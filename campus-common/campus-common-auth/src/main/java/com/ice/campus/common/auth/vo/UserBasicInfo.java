package com.ice.campus.common.auth.vo;

import lombok.Data;

import java.util.Date;

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
     * 用户权限
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;
}
