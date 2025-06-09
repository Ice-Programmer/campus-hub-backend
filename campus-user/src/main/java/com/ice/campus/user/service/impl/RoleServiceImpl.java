package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.Role;
import com.ice.campus.user.service.RoleService;
import com.ice.campus.user.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【role(角色)】的数据库操作Service实现
* @createDate 2025-06-09 10:36:10
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




