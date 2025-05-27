package com.ice.campus.course.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学习记录表
 * @TableName study_record
 */
@TableName(value ="study_record")
@Data
public class StudyRecord implements Serializable {
    /**
     * 记录id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 小节id
     */
    private Long sectionId;

    /**
     * 学习时长（秒）
     */
    private Integer studyTime;

    /**
     * 进度（百分比）
     */
    private Integer progress;

    /**
     * 是否完成：0-未完成，1-已完成
     */
    private Integer isComplete;

    /**
     * 上次学习位置（秒）
     */
    private Integer lastPosition;

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
    private static final long serialVersionUID = 1L;
}