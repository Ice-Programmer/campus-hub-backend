package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.Permission;
import com.ice.campus.user.service.PermissionService;
import com.ice.campus.user.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【permission(权限)】的数据库操作Service实现
* @createDate 2025-06-09 10:36:10
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




