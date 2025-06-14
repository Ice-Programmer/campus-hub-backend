package com.ice.campus.team.controller;

import com.ice.campus.common.auth.annotation.CheckPermission;
import com.ice.campus.common.auth.enums.PermissionEnum;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.team.model.request.team.TeamCreateRequest;
import com.ice.campus.team.model.request.team.TeamEditRequest;
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
}
