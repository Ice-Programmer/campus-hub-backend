package com.ice.campus.course.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户选课表
 * @TableName user_course
 */
@TableName(value ="user_course")
@Data
public class UserCourse implements Serializable {
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
     * 购买时间
     */
    private Date purchaseTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 学习进度（百分比）
     */
    private Integer progress;

    /**
     * 状态：0-禁用，1-正常
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