package com.ice.campus.post.model.request.post;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 帖子收藏 / 取消收藏请求
 */
@Data
public class PostFavourAddRequest implements Serializable {

    /**
     * 帖子 id
     */
    private Long postId;

    @Serial
    private static final long serialVersionUID = 1L;
}