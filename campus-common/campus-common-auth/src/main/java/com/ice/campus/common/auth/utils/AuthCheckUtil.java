package com.ice.campus.common.auth.utils;

import com.ice.campus.common.auth.enums.PermissionEnum;
import com.ice.campus.common.auth.enums.UserRoleEnum;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;

import java.util.Set;

/**
 * 校验用户权限
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 19:11
 */
public class AuthCheckUtil {

    /**
     * 判断当前用户是否为管理员
     *
     * @return 是否为管理员
     */
    public static boolean isCurrentUserAdmin() {
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        return UserRoleEnum.ADMIN.getValue().equals(currentUser.getRole());
    }

    /**
     * 是否拥有权限
     *
     * @param permissionEnums 所需所有权限
     * @return 是否拥有权限
     */
    public static boolean hasAllPermission(PermissionEnum... permissionEnums) {
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        Set<String> permissions = currentUser.getPermissions();
        for (PermissionEnum permissionEnum : permissionEnums) {
            if (!permissions.contains(permissionEnum.getValue())) {
                return false;
            }
        }
        return true;
    }
}
