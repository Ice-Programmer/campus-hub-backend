package com.ice.campus.team.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.cache.constant.TeamConstant;
import com.ice.campus.common.cache.manager.LockManager;
import com.ice.campus.common.core.constant.DatabaseConstant;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.team.constant.TeamCommonConstant;
import com.ice.campus.team.mapper.TeamMemberMapper;
import com.ice.campus.team.model.entity.Team;
import com.ice.campus.team.model.entity.TeamMember;
import com.ice.campus.team.model.enums.TeamCommonMemberEnum;
import com.ice.campus.team.model.enums.TeamMemberStatusEnum;
import com.ice.campus.team.model.enums.TeamStatusEnum;
import com.ice.campus.team.model.request.team.TeamCreateRequest;
import com.ice.campus.team.model.request.team.TeamEditRequest;
import com.ice.campus.team.service.TeamService;
import com.ice.campus.team.mapper.TeamMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjiahan
 * @description 针对表【team(队伍表)】的数据库操作Service实现
 * @createDate 2025-06-11 16:23:18
 */
@Service
@Slf4j
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
        implements TeamService {

    @Resource
    private TeamMemberMapper teamMemberMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private LockManager lockManager;

    private final static Gson GSON = new Gson();

    @Override
    public boolean isTeamCreatorOrManager(Long teamId, Long userId) {
        TeamMember teamMember = teamMemberMapper.selectOne(Wrappers.<TeamMember>lambdaQuery()
                .eq(TeamMember::getTeamId, teamId)
                .eq(TeamMember::getUserId, userId)
                .select(TeamMember::getRoleId)
                .last(DatabaseConstant.LIMIT_ONE));
        ThrowUtils.throwIf(ObjectUtils.isEmpty(teamMember), ErrorCode.NOT_FOUND_ERROR, "队伍信息不存在");

        return TeamCommonMemberEnum.getCreatorAndManagerValueSet().contains(teamMember.getRoleId());
    }

    @Override
    public Long createTeam(TeamCreateRequest teamCreateRequest) {
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();

        // 1. 校验当前用户创建队伍是否到达上限
        int count = baseMapper.countUserTeamNum(currentUser.getId());
        if (count >= TeamCommonConstant.USER_CREATE_MAX_TEAM_NUM) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "用户默认创建队伍数量为 10 ！");
        }

        String lockKey = TeamConstant.TEAM_CREATE_LOCK_PREFIX + currentUser.getId();

        // 加锁防止用户创建多个
        return lockManager.executeWithLock(lockKey,
                0,
                10,
                TimeUnit.SECONDS,
                () -> createInterval(teamCreateRequest, currentUser),
                () -> {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "请勿重复操作");
                }
        );
    }

    @Override
    public boolean editTeam(TeamEditRequest teamEditRequest) {
        // 校验队伍是否存在
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        Long teamId = teamEditRequest.getId();
        Team team = baseMapper.selectOne(Wrappers.<Team>lambdaQuery()
                .eq(Team::getCreatorId, currentUser.getId())
                .eq(Team::getId, teamId)
                .select(Team::getCurrentMembers)
                .last(DatabaseConstant.LIMIT_ONE));
        ThrowUtils.throwIf(ObjectUtils.isEmpty(team), ErrorCode.NOT_FOUND_ERROR, "队伍信息不存在！");

        // 校验人数是否少于当前人数
        if (team.getCurrentMembers() > teamEditRequest.getMaxMembers()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "修改最大人数不得低于当前队伍人数！");
        }

        BeanUtils.copyProperties(teamEditRequest, team);
        team.setTags(GSON.toJson(teamEditRequest.getTagList()));
        try {
            baseMapper.updateById(team);
        } catch (Exception e) {
            log.error("修改队伍失败，队伍 id：{}，修改用户：{}", teamId, currentUser.getId());
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "修改队伍失败！请重试");
        }
        log.info("修改队伍成功，队伍 id：{}，修改用户：{}", teamId, currentUser.getId());
        return true;
    }


    /**
     * 创建队伍（内部方法）
     *
     * @param request     创建请求
     * @param currentUser 创建用户
     * @return 用户 id
     */
    private Long createInterval(TeamCreateRequest request, UserBasicInfo currentUser) {
        Team team = new Team();
        transactionTemplate.executeWithoutResult(status -> {
            try {
                // 2. 插入队伍信息
                BeanUtils.copyProperties(request, team);
                team.setCreatorId(currentUser.getId());
                team.setTags(GSON.toJson(request.getTagList()));
                team.setStatus(TeamStatusEnum.UNDER_REVIEW.getValue());
                baseMapper.insert(team);

                // 3. 插入队伍成员表
                TeamMember teamMember = new TeamMember();
                teamMember.setTeamId(team.getId());
                teamMember.setUserId(currentUser.getId());
                teamMember.setRoleId(TeamCommonMemberEnum.CREATOR.getValue());
                teamMember.setJoinTime(new Date());
                teamMember.setStatus(TeamMemberStatusEnum.IN.getValue());
                teamMemberMapper.insert(teamMember);
            } catch (DuplicateKeyException e) {
                log.error("创建队伍唯一键冲突，request: {}, userId: {}", request, currentUser.getId(), e);
                status.setRollbackOnly();
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "请勿重复插入");
            } catch (Exception e) {
                log.error("创建队伍失败，request: {}, userId: {}", request, currentUser.getId(), e);
                status.setRollbackOnly();
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "队伍创建失败，" + e.getMessage());
            }
        });

        return team.getId();
    }
}




