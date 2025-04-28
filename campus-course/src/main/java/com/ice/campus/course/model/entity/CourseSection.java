package com.ice.campus.course.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程内容表（小节）
 * @TableName course_section
 */
@TableName(value ="course_section")
@Data
public class CourseSection implements Serializable {
    /**
     * 小节id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程id
     */
    private Long courseId;

    /**
     * 章节id
     */
    private Long chapterId;

    /**
     * 小节名称
     */
    private String sectionName;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 视频时长（秒）
     */
    private Integer videoDuration;

    /**
     * 是否免费：0-不免费，1-免费
     */
    private Integer isFree;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 状态：0-未发布，1-已发布
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
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}