package com.ice.campus.post.model.request.post;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 帖子表
 *
 * @TableName post
 */
@Data
public class PostEditRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    @Size(max = 30, message = "帖子标题长度不能超过 30 个字")
    private String title;

    /**
     * 内容
     */
    @Size(max = 2000, message = "帖子内容长度不能超过 2000 个字")
    private String content;

    /**
     * 发布地点
     */
    @Size(max = 50, message = "发布地点信息不能超过 50 个字")
    private String address;

    @Serial
    private static final long serialVersionUID = 1L;
}