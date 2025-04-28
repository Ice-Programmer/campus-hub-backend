package com.ice.campus.course.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程资料表
 * @TableName course_resource
 */
@TableName(value ="course_resource")
@Data
public class CourseResource implements Serializable {
    /**
     * 资料id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 小节id
     */
    private Integer sectionId;

    /**
     * 资料名称
     */
    private String resourceName;

    /**
     * 资料url
     */
    private String resourceUrl;

    /**
     * 资料类型：1-文档，2-视频，3-音频，4-图片，5-其他
     */
    private Integer resourceType;

    /**
     * 文件大小（kb）
     */
    private Integer fileSize;

    /**
     * 下载次数
     */
    private Integer downloadCount;

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