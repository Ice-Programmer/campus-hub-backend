package com.ice.campus.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.campus.user.model.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.user.model.request.tag.TagAddRequest;
import com.ice.campus.user.model.request.tag.TagQueryRequest;
import com.ice.campus.user.model.vo.TagVO;
import jakarta.validation.Valid;

/**
* @author chenjiahan
* @description 针对表【tag(标签表)】的数据库操作Service
* @createDate 2025-06-08 21:55:51
*/
public interface TagService extends IService<Tag> {

    /**
     * 添加标签
     *
     * @param tagAddRequest 标签新增参数
     * @return 标签 id
     */
    Long addTag(TagAddRequest tagAddRequest);

    /**
     * 分页查询标签
     *
     * @param tagQueryRequest 查询条件
     * @return 标签分页
     */
    Page<TagVO> pageTagVO(TagQueryRequest tagQueryRequest);
}
