package com.ice.campus.course.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程评价表
 * @TableName course_review
 */
@TableName(value ="course_review")
@Data
public class CourseReview implements Serializable {
    /**
     * 评价id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评分（1-5）
     */
    private Integer rating;

    /**
     * 是否匿名：0-不匿名，1-匿名
     */
    private Integer isAnonymous;

    /**
     * 状态：0-隐藏，1-显示
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

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}