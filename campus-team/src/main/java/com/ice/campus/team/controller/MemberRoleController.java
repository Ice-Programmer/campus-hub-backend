package com.ice.campus.team.controller;

import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.team.model.request.role.MemberRoleAddRequest;
import com.ice.campus.team.service.MemberRoleService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 16:21
 */
@RestController
@Slf4j
@RequestMapping("/member/role")
public class MemberRoleController {

    @Resource
    private MemberRoleService memberRoleService;

    /**
     * 新增队伍成员角色
     *
     * @param memberRoleAddRequest 队伍角色添加请求
     * @return 添加成功
     */
    @PostMapping("/add")
    public BaseResponse<Long> addMemberRole(@RequestBody @Valid MemberRoleAddRequest memberRoleAddRequest) {
        if (memberRoleAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return ResultUtils.success(memberRoleService.addMemberRole(memberRoleAddRequest));
    }
}
