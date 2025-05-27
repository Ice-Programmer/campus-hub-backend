package com.ice.campus.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.chat.model.entity.User;
import com.ice.campus.chat.service.UserService;
import com.ice.campus.chat.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-05-22 18:38:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




