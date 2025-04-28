package com.ice.campus.post.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.post.model.entity.Post;
import com.ice.campus.post.model.request.post.PostAddRequest;
import com.ice.campus.post.model.request.post.PostEditRequest;
import com.ice.campus.post.model.request.post.PostQueryRequest;
import com.ice.campus.post.model.vo.PostVO;
import com.ice.campus.post.service.PostService;
import com.ice.campus.post.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【post(帖子表)】的数据库操作Service实现
* @createDate 2025-04-21 20:50:38
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

    @Override
    public long addPost(PostAddRequest postAddRequest) {
        return 0;
    }

    @Override
    public boolean editPost(PostEditRequest postEditRequest) {
        return false;
    }

    @Override
    public boolean deletePost(Long id) {
        return false;
    }

    @Override
    public PostVO getPostVO(Long id) {
        return null;
    }

    @Override
    public Page<PostVO> pagePostVO(PostQueryRequest postQueryRequest) {
        return null;
    }
}




