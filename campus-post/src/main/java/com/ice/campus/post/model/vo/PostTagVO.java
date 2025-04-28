package com.ice.campus.post.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 标签 视图类
 *
 * @author <a href="https://github.com/IceProgramer">chenjiahan</a>
 * @create 2025/1/21 12:44
 */
@Data
public class PostTagVO implements Serializable {

    /**
     * id
     */
    private Long id;


    /**
     * 帖子 id
     */
    @JsonIgnore
    private Long postId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 标签名称
     */
    private String name;

    @Serial
    private static final long serialVersionUID = 6153440691965896596L;
}
