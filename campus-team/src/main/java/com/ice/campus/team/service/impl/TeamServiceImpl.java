package com.ice.campus.team.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.core.constant.DatabaseConstant;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.team.mapper.TeamMemberMapper;
import com.ice.campus.team.model.entity.Team;
import com.ice.campus.team.model.entity.TeamMember;
import com.ice.campus.team.model.enums.TeamCommonMemberEnum;
import com.ice.campus.team.service.TeamService;
import com.ice.campus.team.mapper.TeamMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author chenjiahan
 * @description 针对表【team(队伍表)】的数据库操作Service实现
 * @createDate 2025-06-11 16:23:18
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
        implements TeamService {

    @Resource
    private TeamMemberMapper teamMemberMapper;

    @Override
    public boolean isTeamCreatorOrManager(Long teamId, Long userId) {
        TeamMember teamMember = teamMemberMapper.selectOne(Wrappers.<TeamMember>lambdaQuery()
                .eq(TeamMember::getTeamId, teamId)
                .eq(TeamMember::getUserId, userId)
                .select(TeamMember::getRoleId)
                .last(DatabaseConstant.LIMIT_ONE));
        ThrowUtils.throwIf(ObjectUtils.isEmpty(teamMember), ErrorCode.NOT_FOUND_ERROR, "队伍信息不存在");

        return TeamCommonMemberEnum.getCreatorAndManagerValueSet().contains(teamMember.getRoleId().intValue());
    }
}




