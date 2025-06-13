package com.ice.campus.team.service;

import com.ice.campus.team.model.entity.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.team.model.request.team.TeamCreateRequest;
import jakarta.validation.Valid;

/**
 * @author chenjiahan
 * @description 针对表【team(队伍表)】的数据库操作Service
 * @createDate 2025-06-11 16:23:18
 */
public interface TeamService extends IService<Team> {

    /**
     * 是否为小队创建者或管理员
     *
     * @param teamId 队伍 id
     * @param userId 用户 id
     * @return 是否为小队创建者或管理员
     */
    boolean isTeamCreatorOrManager(Long teamId, Long userId);

    /**
     * 创建队伍
     *
     * @param teamCreateRequest 队伍创建请求
     * @return 创建队伍 id
     */
    Long createTeam(TeamCreateRequest teamCreateRequest);
}
