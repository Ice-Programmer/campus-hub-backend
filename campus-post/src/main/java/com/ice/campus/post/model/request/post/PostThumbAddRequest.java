package com.ice.campus.post.model.request.post;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 帖子点赞请求
 *
 * @author <a href="https://github.com/IceProgramer">chenjiahan</a>
 * @create 2025/1/22 09:38
 */
@Data
public class PostThumbAddRequest implements Serializable {


    /**
     * 帖子 id
     */
    private Long postId;

    @Serial
    private static final long serialVersionUID = 3522452346708173791L;
}
