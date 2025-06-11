package com.ice.campus.team.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 队伍成员角色表
 * @TableName member_role
 */
@TableName(value ="member_role")
@Data
public class MemberRole implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建用户 id
     */
    private Long createUserId;

    /**
     * 队伍 id（可以关联队伍）
     */
    private Long teamId;

    /**
     * 介绍
     */
    private String description;

    /**
     * 是否官方(0-不是，1-是)
     */
    private Integer isOfficial;

    /**
     * 角色名称
     */
    private String roleName;

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

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}