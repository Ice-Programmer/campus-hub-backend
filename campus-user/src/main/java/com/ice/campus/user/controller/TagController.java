package com.ice.campus.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.user.model.request.tag.TagAddRequest;
import com.ice.campus.user.model.request.tag.TagQueryRequest;
import com.ice.campus.user.model.vo.TagVO;
import com.ice.campus.user.service.TagService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/8 21:56
 */
@RestController
@RequestMapping("/user/tag")
@Slf4j
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 添加标签
     *
     * @param tagAddRequest 标签新增参数
     * @return 标签 id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addTag(@RequestBody @Valid TagAddRequest tagAddRequest) {
        return ResultUtils.success(tagService.addTag(tagAddRequest));
    }

    /**
     * 分页查询标签
     *
     * @param tagQueryRequest 查询条件
     * @return 标签分页
     */
    @PostMapping("/page/vo")
    public BaseResponse<Page<TagVO>> pageTagVO(@RequestBody TagQueryRequest tagQueryRequest) {
        long pageSize = tagQueryRequest.getPageSize();
        if (pageSize > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "查询数量最大不超过 50 !");
        }
        return ResultUtils.success(tagService.pageTagVO(tagQueryRequest));
    }
}
