package com.ice.campus.team.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.campus.common.auth.annotation.CheckPermission;
import com.ice.campus.common.auth.enums.PermissionEnum;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.team.model.request.team.TeamCreateRequest;
import com.ice.campus.team.model.request.team.TeamEditRequest;
import com.ice.campus.team.model.request.team.TeamJoinRequest;
import com.ice.campus.team.model.request.team.TeamQueryRequest;
import com.ice.campus.team.model.vo.TeamVO;
import com.ice.campus.team.service.TeamService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:18
 */
@RestController
@RequestMapping("/team")
@Slf4j
public class TeamController {

    @Resource
    private TeamService teamService;

    /**
     * 创建队伍
     *
     * @param teamCreateRequest 队伍创建请求
     * @return 创建队伍 id
     */
    @PostMapping("/create")
    @CheckPermission(PermissionEnum.TEAM_CREATE)
    public BaseResponse<Long> createTeam(@RequestBody @Valid TeamCreateRequest teamCreateRequest) {
        return ResultUtils.success(teamService.createTeam(teamCreateRequest));
    }

    /**
     * 编辑队伍
     *
     * @param teamEditRequest 队伍编辑请求
     * @return 编辑成功
     */
    @PostMapping("/edit")
    @CheckPermission(PermissionEnum.TEAM_CREATE)
    public BaseResponse<Boolean> editTeam(@RequestBody @Valid TeamEditRequest teamEditRequest) {
        return ResultUtils.success(teamService.editTeam(teamEditRequest));
    }

    /**
     * 根据 id 获取队伍信息
     *
     * @param teamId 队伍 id
     * @return 队伍信息
     */
    @GetMapping("/get/vo/{id}")
    public BaseResponse<TeamVO> getTeamVO(@PathVariable("id") Long teamId) {
        if (teamId == null || teamId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍 id 为空！");
        }

        return ResultUtils.success(teamService.getTeamVOById(teamId));
    }


    /**
     * 获取队伍分页
     *
     * @param teamQueryRequest 队伍查询条件
     * @return 队伍分页
     */
    @PostMapping("/page/vo")
    public BaseResponse<Page<TeamVO>> pageTeamVO(@RequestBody @Valid TeamQueryRequest teamQueryRequest) {
        long pageSize = teamQueryRequest.getPageSize();
        if (pageSize > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "每页最大不超过50");
        }

        Page<TeamVO> postVOPage = teamService.pageTeamVO(teamQueryRequest);

        return ResultUtils.success(postVOPage);
    }

    /**
     * 加入队伍
     *
     * @param teamJoinRequest 队伍加入请求
     * @return 加入成功
     */
    @CheckPermission(PermissionEnum.TEAM_JOIN)
    @PostMapping("/join")
    public BaseResponse<Boolean> joinTeam(@RequestBody @Valid TeamJoinRequest teamJoinRequest) {
        if (teamJoinRequest == null || teamJoinRequest.getTeamId() == null || teamJoinRequest.getTeamId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Boolean result = teamService.joinTeam(teamJoinRequest);

        return ResultUtils.success(result);
    }
}
