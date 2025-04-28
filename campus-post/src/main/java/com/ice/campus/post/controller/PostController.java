package com.ice.campus.post.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.DeleteRequest;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.post.model.request.post.PostAddRequest;
import com.ice.campus.post.model.request.post.PostEditRequest;
import com.ice.campus.post.model.request.post.PostQueryRequest;
import com.ice.campus.post.model.vo.PostVO;
import com.ice.campus.post.service.PostService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 帖子控制器
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/4/21 20:52
 */
@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Resource
    private PostService postService;

    /**
     * 用户发布帖子接口
     *
     * @param postAddRequest 发布帖子内容
     * @return 帖子 id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@Valid @RequestBody PostAddRequest postAddRequest) {
        if (postAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "发布帖子内容为空！");
        }

        long postId = postService.addPost(postAddRequest);

        return ResultUtils.success(postId);
    }

    /**
     * 编辑帖子内容接口
     *
     * @param postEditRequest 帖子编辑请求
     * @return 编辑成功
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editPost(@Valid @RequestBody PostEditRequest postEditRequest) {
        ThrowUtils.throwIf(postEditRequest == null, ErrorCode.PARAMS_ERROR, "编辑帖子内容为空！");
        if (postEditRequest.getId() == null || postEditRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "帖子 id 错误");
        }

        boolean result = postService.editPost(postEditRequest);

        return ResultUtils.success(result);
    }

    /**
     * 删除帖子接口
     *
     * @param deleteRequest 删除请求
     * @return 删除成功
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null, ErrorCode.PARAMS_ERROR);
        Long id = deleteRequest.getId();
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "删除帖子 id 错误！");
        }

        boolean result = postService.deletePost(deleteRequest.getId());

        return ResultUtils.success(result);
    }


    /**
     * 根据 id 获取帖子 VO
     *
     * @return 帖子 VO
     */
    @GetMapping("/{id}")
    public BaseResponse<PostVO> getPostVO(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "获取帖子 id 不能为空");
        }

        PostVO postVO = postService.getPostVO(id);

        return ResultUtils.success(postVO);
    }

    /**
     * 分页搜索帖子信息
     *
     * @param postQueryRequest 帖子搜索请求类
     * @return 帖子分页
     */
    @PostMapping("/page")
    public BaseResponse<Page<PostVO>> pagePostVO(@RequestBody PostQueryRequest postQueryRequest) {
        long pageSize = postQueryRequest.getPageSize();
        if (pageSize > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "每页最大不超过50");
        }
        Page<PostVO> postVOPage = postService.pagePostVO(postQueryRequest);

        return ResultUtils.success(postVOPage);
    }
}
