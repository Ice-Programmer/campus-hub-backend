package com.ice.campus.common.auth.annotation;

import com.ice.campus.common.auth.enums.PermissionEnum;

import java.lang.annotation.*;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/9 14:14
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {

    /**
     * 所需权限
     */
    PermissionEnum[] value() default {};

    /**
     * 逻辑关系
     */
    Logical logical() default Logical.AND;

    /**
     * 是否允许超级管理员跳过权限检查
     */
    boolean allowSuperAdmin() default true;

    /**
     * 逻辑关系枚举
     */
    enum Logical {
        /**
         * 且（需要同时拥有所有权限）
         */
        AND,

        /**
         * 或（拥有其中一个权限即可）
         */
        OR
    }
}
