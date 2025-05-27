package com.ice.campus.course.model.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程基本信息表
 * @TableName course
 */
@Data
public class CourseVO implements Serializable {
    /**
     * 课程id
     */
    private Long id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 讲师id
     */
    private Long teacherInfo;

    /**
     * 封面图片url
     */
    private String coverImg;

    /**
     * 课程描述
     */
    private String description;

    /**
     * 课程价格
     */
    private BigDecimal price;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 课时数
     */
    private Integer courseHours;

    /**
     * 难度级别：1-入门，2-初级，3-中级，4-高级，5-专家
     */
    private Integer difficultyLevel;

    /**
     * 状态：0-未发布，1-已发布，2-下架
     */
    private Integer status;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 购买人数
     */
    private Integer buyCount;

    /**
     * 是否免费：0-收费，1-免费
     */
    private Integer isFree;

    /**
     * 是否推荐：0-不推荐，1-推荐
     */
    private Integer isRecommend;

    /**
     * 创建时间
     */
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}