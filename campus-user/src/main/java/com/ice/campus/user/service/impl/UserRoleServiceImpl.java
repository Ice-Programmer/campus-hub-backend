package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.UserRole;
import com.ice.campus.user.service.UserRoleService;
import com.ice.campus.user.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【user_role(用户角色关联)】的数据库操作Service实现
* @createDate 2025-06-09 10:36:10
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




