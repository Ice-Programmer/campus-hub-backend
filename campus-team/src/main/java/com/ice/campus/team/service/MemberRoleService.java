package com.ice.campus.team.service;

import com.ice.campus.team.model.entity.MemberRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.team.model.request.role.MemberRoleAddRequest;
import jakarta.validation.Valid;

/**
 * @author chenjiahan
 * @description 针对表【member_role(队伍成员角色表)】的数据库操作Service
 * @createDate 2025-06-11 16:23:18
 */
public interface MemberRoleService extends IService<MemberRole> {

    /**
     * 新增队伍成员角色
     *
     * @param memberRoleAddRequest 队伍角色添加请求
     * @return 添加成功
     */
    Long addMemberRole(MemberRoleAddRequest memberRoleAddRequest);

}
