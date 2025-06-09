package com.ice.campus.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import lombok.Data;

/**
 * 用户
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 0-女 1-男 2
     */
    private Integer gender;

    /**
     * 学校
     */
    private Long university;

    /**
     * 学历
     */
    private Integer education;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 城市
     */
    private Long city;

    /**
     * 粉丝数
     */
    private Integer followeeNum;

    /**
     * 关注数
     */
    private Integer followNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}