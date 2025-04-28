package com.ice.campus.post.model.request.post;

import com.ice.campus.common.core.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * 帖子分页查询请求类
 *
 * @author <a href="https://github.com/IceProgramer">chenjiahan</a>
 * @create 2025/1/21 13:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PostQueryRequest extends PageRequest implements Serializable {

    /**
     * post id
     */
    private Long id;

    /**
     * post id 列表
     */
    private List<Long> ids;

    /**
     * 文本搜索内容
     */
    private String searchText;

    /**
     * 标题搜索
     */
    private String title;

    /**
     * 内容搜索
     */
    private String content;

    /**
     * 地理位置搜索
     */
    private String address;

    /**
     * 创作者搜索
     */
    private Long userId;

    /**
     * 标签搜索
     */
    private List<Long> tagIdList;

    @Serial
    private static final long serialVersionUID = -1070038973112042998L;
}
