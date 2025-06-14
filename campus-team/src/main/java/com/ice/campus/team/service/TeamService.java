package com.ice.campus.team.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.team.model.entity.Team;
import com.ice.campus.team.model.request.team.TeamCreateRequest;
import com.ice.campus.team.model.request.team.TeamEditRequest;
import com.ice.campus.team.model.request.team.TeamJoinRequest;
import com.ice.campus.team.model.request.team.TeamQueryRequest;
import com.ice.campus.team.model.vo.TeamVO;

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

    /**
     * 编辑队伍
     *
     * @param teamEditRequest 队伍编辑请求
     * @return 编辑成功
     */
    boolean editTeam(TeamEditRequest teamEditRequest);

    /**
     * 根据 id 获取队伍信息
     *
     * @param teamId 队伍 id
     * @return 队伍信息
     */
    TeamVO getTeamVOById(Long teamId);

    /**
     * 获取队伍分页
     *
     * @param teamQueryRequest 队伍查询条件
     * @return 队伍分页
     */
    Page<TeamVO> pageTeamVO(TeamQueryRequest teamQueryRequest);

    /**
     * 加入队伍
     *
     * @param teamJoinRequest 队伍加入请求
     * @return 加入成功
     */
    Boolean joinTeam(TeamJoinRequest teamJoinRequest);
}
