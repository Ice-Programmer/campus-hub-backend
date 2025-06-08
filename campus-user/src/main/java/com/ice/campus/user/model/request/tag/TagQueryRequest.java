package com.ice.campus.user.model.request.tag;

import com.ice.campus.common.core.common.PageRequest;
import com.ice.campus.common.core.validation.annotation.EnumCheck;
import com.ice.campus.user.model.enums.TagStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/8 22:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagQueryRequest extends PageRequest implements Serializable {

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
     * 创作者搜索
     */
    private Long userId;

    @Serial
    private static final long serialVersionUID = -2340582001659412419L;
}
