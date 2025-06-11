package com.ice.campus.team.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.auth.enums.PermissionEnum;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.utils.AuthCheckUtil;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.enums.OfficialEnum;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.team.mapper.MemberRoleMapper;
import com.ice.campus.team.model.entity.MemberRole;
import com.ice.campus.team.model.request.role.MemberRoleAddRequest;
import com.ice.campus.team.service.MemberRoleService;
import com.ice.campus.team.service.TeamService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenjiahan
 * @description 针对表【member_role(队伍成员角色表)】的数据库操作Service实现
 * @createDate 2025-06-11 16:23:18
 */
@Service
@Slf4j
public class MemberRoleServiceImpl extends ServiceImpl<MemberRoleMapper, MemberRole>
        implements MemberRoleService {

    @Resource
    private TeamService teamService;

    @Override
    public Long addMemberRole(MemberRoleAddRequest memberRoleAddRequest) {
        Integer isOfficial = memberRoleAddRequest.getIsOfficial();
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        MemberRole memberRole = new MemberRole();
        if (OfficialEnum.OFFICIAL.getValue().equals(isOfficial)) {
            // 判断是否拥有管理队伍权限
            // 创建官方角色
            ThrowUtils.throwIf(!AuthCheckUtil.hasAllPermission(PermissionEnum.TEAM_MANAGE),
                    ErrorCode.NO_AUTH_ERROR, "没有创建成员角色权利");
            memberRole.setIsOfficial(OfficialEnum.OFFICIAL.getValue());
            log.info("管理员 {} 创建队伍角色成员：「{}」", currentUser.getId(), memberRoleAddRequest.getRoleName());
        } else {
            // 队长或管理员创建
            // 创建小队角色
            Long teamId = memberRoleAddRequest.getTeamId();
            boolean result = teamService.isTeamCreatorOrManager(teamId, currentUser.getId());
            ThrowUtils.throwIf(result, ErrorCode.NO_AUTH_ERROR, "仅有队伍创建者和管理员可创建队伍角色！");
            memberRole.setIsOfficial(OfficialEnum.UNOFFICIAL.getValue());
            memberRole.setTeamId(teamId);
            log.info("队伍 「{}」 创建队伍角色：「{}」，创建者: {}", teamId, memberRoleAddRequest.getRoleName(), currentUser.getId());
        }
        memberRole.setCreateUserId(currentUser.getId());
        memberRole.setDescription(memberRoleAddRequest.getDescription());
        memberRole.setRoleName(memberRoleAddRequest.getRoleName());
        baseMapper.insert(memberRole);

        return memberRole.getId();
    }
}




