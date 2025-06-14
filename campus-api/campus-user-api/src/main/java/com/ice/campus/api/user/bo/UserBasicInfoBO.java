package com.ice.campus.api.user.bo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户基础查询结果
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/13 23:36
 */
@Data
public class UserBasicInfoBO implements Serializable {

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 学校 id
     */
    private Long schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    @Serial
    private static final long serialVersionUID = 2749592017777951192L;
}
