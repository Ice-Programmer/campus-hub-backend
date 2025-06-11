package com.ice.campus.team.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 队伍表
 * @TableName team
 */
@TableName(value ="team")
@Data
public class Team implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 队伍名称
     */
    private String teamName;

    /**
     * 队伍描述
     */
    private String description;

    /**
     * 队长用户id
     */
    private Long leaderId;

    /**
     * 最大成员数
     */
    private Integer maxMembers;

    /**
     * 当前成员数
     */
    private Integer currentMembers;

    /**
     * 队伍类型：1-学习小组，2-项目团队，3-竞赛队伍，4-社团组织
     */
    private Integer teamType;

    /**
     * 状态：0-解散，1-招募中，2-活动中，3-停止招募
     */
    private Integer status;

    /**
     * 是否公开：0-私有，1-公开
     */
    private Integer isPublic;

    /**
     * 加入是否需要审批
     */
    private Integer isApply;

    /**
     * 标签（JSON格式）
     */
    private String tags;

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