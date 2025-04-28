package com.ice.campus.post.model.vo;

import com.ice.campus.common.auth.vo.UserBasicInfo;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子 视图类
 *
 * @author <a href="https://github.com/IceProgramer">chenjiahan</a>
 * @create 2025/1/21 12:31
 */
@Data
public class PostVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 发布地点
     */
    private String address;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 关联标签列表
     */
    private List<PostTagVO> tagList;

    /**
     * 图片列表
     */
    private List<String> imageList;

    /**
     * 封面图片
     */
    private Integer coverIndex;

    /**
     * 用户信息
     */
    private UserBasicInfo createUser;

    /**
     * 是否收藏
     */
    private Boolean hasFavour = false;

    /**
     * 是否点赞
     */
    private Boolean hasThumb = false;

    /**
     * 创建时间
     */
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 90132243746206497L;
}
