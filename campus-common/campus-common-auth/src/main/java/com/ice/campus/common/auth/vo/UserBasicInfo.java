package com.ice.campus.common.auth.vo;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/10 10:49
 */
@Data
public class UserBasicInfo {

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户角色
     */
    private String role;

    /**
     * 用户权限
     */
    private Set<String> permissions;

    /**
     * 数据权限范围
     */
    private Integer dataScope;

    /**
     * 学校 id
     */
    private Long schoolId;

    /**
     * 创建时间
     */
    private Date createTime;
}
