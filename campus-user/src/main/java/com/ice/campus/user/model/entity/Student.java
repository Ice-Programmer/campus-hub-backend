package com.ice.campus.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 学生
 *
 * @TableName student
 */
@TableName(value = "student")
@Data
public class Student implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 学生年级
     */
    private Integer studentGrade;

    /**
     * 学校 id
     */
    private Long schoolId;

    /**
     * 学生专业
     */
    private Long studentMajor;

    /**
     * 入学年份
     */
    private Integer enrollmentYear;

    /**
     * 预计毕业年份
     */
    private Integer graduationYear;

    /**
     * 学生状态:1-在读 2-休学 3-毕业 4-退学
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