package com.ice.campus.team.controller;

import com.ice.campus.common.auth.annotation.CheckPermission;
import com.ice.campus.common.auth.enums.PermissionEnum;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.team.model.request.team.TeamCreateRequest;
import com.ice.campus.team.model.request.team.TeamEditRequest;
import com.ice.campus.team.service.TeamService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
