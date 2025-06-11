package com.ice.campus.team.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 队伍成员表
 * @TableName team_member
 */
@TableName(value ="team_member")
@Data
public class TeamMember implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 队伍id
     */
    private Long teamId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色 id
     */
    private Long roleId;

    /**
     * 加入时间
     */
    private Date joinTime;

    /**
     * 状态：0-已退出，1-正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}