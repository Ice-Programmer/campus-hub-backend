package com.ice.campus.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.core.constant.CommonConstant;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.common.core.utils.SqlUtils;
import com.ice.campus.user.model.entity.Tag;
import com.ice.campus.user.model.enums.TagStatusEnum;
import com.ice.campus.user.model.request.tag.TagAddRequest;
import com.ice.campus.user.model.request.tag.TagQueryRequest;
import com.ice.campus.user.model.vo.TagVO;
import com.ice.campus.user.service.TagService;
import com.ice.campus.user.mapper.TagMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjiahan
 * @description 针对表【tag(标签表)】的数据库操作Service实现
 * @createDate 2025-06-08 21:55:51
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Override
    public Long addTag(TagAddRequest tagAddRequest) {
        String tagName = tagAddRequest.getTagName();
        ThrowUtils.throwIf(StringUtils.isBlank(tagName), ErrorCode.PARAMS_ERROR, "标签名称不能为空！");
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        Tag tag = new Tag();
        tag.setTagName(tagName);
        // todo 判断身份（管理员直接审核通过）
        tag.setStatus(TagStatusEnum.PENDING.getValue());
        tag.setCreateUserId(currentUser.getId());
        try {
            boolean save = this.save(tag);
            ThrowUtils.throwIf(!save, ErrorCode.OPERATION_ERROR, "插入标签失败");
        } catch (DuplicateKeyException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "标签已存在或正在审批中！");
        }
        return tag.getId();
    }

    @Override
    public Page<TagVO> pageTagVO(TagQueryRequest tagQueryRequest) {
        long current = tagQueryRequest.getCurrent();
        long size = tagQueryRequest.getPageSize();

        Page<Tag> tagPage = baseMapper.selectPage(new Page<>(current, size),
                getQueryWrapper(tagQueryRequest));

        List<Tag> tagList = tagPage.getRecords();
        List<TagVO> tagVOList = tagList.stream().map(TagVO::objToVO).toList();


        Page<TagVO> tagVOPage = new Page<>(tagPage.getCurrent(), tagPage.getSize(), tagPage.getTotal());

        tagVOPage.setRecords(tagVOList);

        return tagVOPage;
    }

    public QueryWrapper<Tag> getQueryWrapper(TagQueryRequest tagQueryRequest) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        if (tagQueryRequest == null) {
            return wrapper;
        }
        // 根据 id 查询
        Long id = tagQueryRequest.getId();
        wrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);

        // 根据 id 列表查询
        List<Long> ids = tagQueryRequest.getIds();
        wrapper.in(CollUtil.isNotEmpty(ids), "id", ids);

        // 根据标签名称查询
        String searchText = tagQueryRequest.getSearchText();
        wrapper.like(StringUtils.isNotBlank(searchText), "tag_name", searchText);

        // 根据创建用户查询
        Long userId = tagQueryRequest.getUserId();
        wrapper.eq(ObjectUtils.isNotEmpty(userId), "create_user_id", userId);

        wrapper.eq("status", TagStatusEnum.APPROVED.getValue());
        wrapper.eq("is_delete", false);


        String sortField = tagQueryRequest.getSortField();
        String sortOrder = tagQueryRequest.getSortOrder();
        wrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        return wrapper;
    }
}




