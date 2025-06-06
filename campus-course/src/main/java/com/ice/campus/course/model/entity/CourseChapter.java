package com.ice.campus.course.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程章节表
 * @TableName course_chapter
 */
@TableName(value ="course_chapter")
@Data
public class CourseChapter implements Serializable {
    /**
     * 章节id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 章节名称
     */
    private String chapterName;

    /**
     * 排序字段
     */
    private Integer sort;

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