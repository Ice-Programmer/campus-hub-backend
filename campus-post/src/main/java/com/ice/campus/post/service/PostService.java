package com.ice.campus.post.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.campus.post.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.post.model.request.post.PostAddRequest;
import com.ice.campus.post.model.request.post.PostEditRequest;
import com.ice.campus.post.model.request.post.PostQueryRequest;
import com.ice.campus.post.model.vo.PostVO;


/**
* @author chenjiahan
* @description 针对表【post(帖子表)】的数据库操作Service
* @createDate 2025-04-21 20:50:38
*/
public interface PostService extends IService<Post> {

    /**
     * 用户发布帖子接口
     *
     * @param postAddRequest 发布帖子内容
     * @return 帖子 id
     */
    long addPost(PostAddRequest postAddRequest);

    /**
     * 编辑帖子内容接口
     *
     * @param postEditRequest 帖子编辑请求
     * @return 编辑成功
     */
    boolean editPost(PostEditRequest postEditRequest);

    /**
     * 删除帖子接口
     *
     * @param id 删除 id
     * @return 删除成功
     */
    boolean deletePost(Long id);

    /**
     * 根据 id 获取帖子 VO
     *
     * @return 帖子 VO
     */
    PostVO getPostVO(Long id);

    /**
     * 分页搜索帖子信息
     *
     * @param postQueryRequest 帖子搜索请求类
     * @return 帖子分页
     */
    Page<PostVO> pagePostVO(PostQueryRequest postQueryRequest);
}
