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
 * 队伍申请表
 * @TableName team_application
 */
@TableName(value ="team_application")
@Data
public class TeamApplication implements Serializable {
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
     * 申请用户id
     */
    private Long userId;

    /**
     * 申请留言
     */
    private String message;

    /**
     * 状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer status;

    /**
     * 处理人id
     */
    private Long processedBy;

    /**
     * 处理时间
     */
    private Date processedTime;

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