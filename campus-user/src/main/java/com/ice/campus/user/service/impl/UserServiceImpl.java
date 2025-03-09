package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.mapper.UserMapper;
import com.ice.campus.user.model.entity.User;
import com.ice.campus.user.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-03-09 22:12:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




