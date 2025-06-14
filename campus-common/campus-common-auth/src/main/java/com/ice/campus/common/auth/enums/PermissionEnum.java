package com.ice.campus.common.auth.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 核心权限枚举
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/9 10:56
 */
@Getter
public enum PermissionEnum implements BaseEnum<String> {

    // ==================== 系统管理权限 ====================
    /**
     * 系统管理权限
     */
    SYSTEM_MANAGE("system:manage", "系统管理"),

    /**
     * 用户管理权限
     */
    USER_MANAGE("user:manage", "用户管理"),

    /**
     * 角色管理权限
     */
    ROLE_MANAGE("role:manage", "角色管理"),

    /**
     * 权限管理权限
     */
    PERMISSION_MANAGE("permission:manage", "权限管理"),

    // ==================== 内容管理权限 ====================
    /**
     * 内容管理权限
     */
    CONTENT_MANAGE("content:manage", "内容管理"),

    /**
     * 创建帖子权限
     */
    POST_CREATE("post:create", "创建帖子"),

    /**
     * 编辑帖子权限
     */
    POST_EDIT("post:edit", "编辑帖子"),

    /**
     * 删除帖子权限
     */
    POST_DELETE("post:delete", "删除帖子"),

    // ==================== 队伍管理权限 ====================

    /**
     * 队伍管理权限
     */
    TEAM_MANAGE("team:manage", "队伍管理"),

    /**
     * 创建队伍权限
     */
    TEAM_CREATE("team:create", "创建队伍"),

    /**
     * 解散队伍权限
     */
    TEAM_DISBAND("team:disband", "解散队伍"),

    /**
     * 解散队伍权限
     */
    TEAM_JOIN("team:join", "加入队伍"),

    // ==================== 其他功能权限 ====================
    /**
     * 查看仪表板权限
     */
    DASHBOARD_VIEW("dashboard:view", "查看仪表板"),

    /**
     * 设置管理权限
     */
    SETTINGS_MANAGE("settings:manage", "设置管理");


    private final String text;

    private final String value;

    PermissionEnum(String value, String text) {
        this.text = text;
        this.value = value;
    }
}
