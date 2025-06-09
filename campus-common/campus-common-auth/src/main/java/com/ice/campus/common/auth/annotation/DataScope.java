package com.ice.campus.common.auth.annotation;

/**
 * 数据权限注解
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/9 16:17
 */
public @interface DataScope {

    /**
     * 数据权限范围
     */
    ScopeType value() default ScopeType.SELF;

    /**
     * 是否允许超级管理员查看所有数据
     */
    boolean allowSuperAdmin() default true;

    /**
     * 数据权限范围枚举
     */
    enum ScopeType {
        /**
         * 全部数据权限
         */
        ALL,

        /**
         * 本部门数据权限
         */
        SCHOOL,

        /**
         * 本部门及以下数据权限
         */
        SCHOOL_AND_CHILD,

        /**
         * 仅本人数据权限
         */
        SELF,

        /**
         * 自定义数据权限
         */
        CUSTOM
    }
}
