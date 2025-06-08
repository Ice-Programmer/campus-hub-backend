package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.UserTag;
import com.ice.campus.user.service.UserTagService;
import com.ice.campus.user.mapper.UserTagMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【user_tag(用户标签关联表)】的数据库操作Service实现
* @createDate 2025-06-08 21:53:39
*/
@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag>
    implements UserTagService{

}




