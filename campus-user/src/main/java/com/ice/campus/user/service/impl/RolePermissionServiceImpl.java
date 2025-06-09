package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.RolePermission;
import com.ice.campus.user.service.RolePermissionService;
import com.ice.campus.user.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【role_permission(角色权限关联)】的数据库操作Service实现
* @createDate 2025-06-09 10:36:10
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




