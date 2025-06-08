package com.ice.campus.user.model.vo;

import com.ice.campus.user.model.entity.Tag;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 标签 VO
 */
@Data
public class TagVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建用户 id
     */
    private Long createUserId;

    /**
     * 是否官方标签(0 - 非 1 - 是)
     */
    private Integer isOfficial;

    /**
     * 使用次数
     */
    private Integer usageCount;

    /**
     * 创建时间
     */
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 9173132870866071927L;

    public static TagVO objToVO(Tag tag) {
        TagVO tagVO = new TagVO();
        BeanUtils.copyProperties(tag, tagVO);
        return tagVO;
    }
}