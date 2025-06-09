package com.ice.campus.common.auth.aspect;

import com.ice.campus.common.auth.annotation.CheckPermission;
import com.ice.campus.common.auth.enums.PermissionEnum;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限校验切面
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/9 14:20
 */
@Slf4j
@Aspect
@Component
public class PermissionCheckAspect {

    @Before("@annotation(checkPermission)")
    public void check(JoinPoint joinPoint, CheckPermission checkPermission) {
        // 获取当前登陆用户
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        ThrowUtils.throwIf(currentUser == null, ErrorCode.NOT_LOGIN_ERROR);
        Set<String> userPermissionSet = Optional.ofNullable(currentUser.getPermissions())
                .orElse(Collections.emptySet());

        // 获取权限信息
        String[] permissions = getPermissions(checkPermission.value());
        if (permissions.length == 0) {
            return;
        }

        // 校验权限
        boolean hasPermission = checkPermissions(userPermissionSet, permissions, checkPermission.logical());

        if (!hasPermission) {
            String message = String.format("用户【%s】访问【%s】权限不足，需要权限:【%s】, 用户权限: 【%s】",
                    currentUser.getUsername(),
                    joinPoint.getSignature().toShortString(),
                    Arrays.toString(permissions),
                    userPermissionSet);
            log.warn(message);
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, message);
        }
    }


    /**
     * 获取权限列表
     *
     * @param permissions 权限枚举
     * @return 权限信息
     */
    private String[] getPermissions(PermissionEnum[] permissions) {
        return Arrays.stream(permissions).map(PermissionEnum::getValue).toArray(String[]::new);
    }

    /**
     * 校验用户权限
     *
     * @param userPermissionSet   当前用户权限集合
     * @param requiredPermissions 需要用户权限
     * @param logical             判断逻辑
     * @return 是否拥有足够权限
     */
    private boolean checkPermissions(Set<String> userPermissionSet, String[] requiredPermissions, CheckPermission.Logical logical) {
        Set<String> requiredSet = Arrays.stream(requiredPermissions).collect(Collectors.toSet());

        if (logical == CheckPermission.Logical.AND) {
            // 需要拥有所有权限
            return userPermissionSet.containsAll(requiredSet);
        } else {
            return requiredSet.stream().anyMatch(userPermissionSet::contains);
        }
    }

}

