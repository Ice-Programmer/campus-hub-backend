package com.ice.campus.api.user.service;


import com.ice.campus.api.user.bo.UserBasicInfoBO;

import java.util.Set;

/**
 * 用户基础信息调用 - RPC 接口
 *
 * @author <a href="https://github.com/IceProgramer">chenjiahan</a>
 * @create 2025/1/18 14:47
 */
public interface UserBasicRpcService {

    /**
     * 根据用户 id 获取用户基础信息
     *
     * @param userId 用户 id
     * @return 用户基础信息
     */
    UserBasicInfoBO getUserBasicInfoByUserId(Long userId);

    /**
     * 批量获取用户 id
     *
     * @param userIds 用户 id 列表
     * @return 用户基础信息列表
     */
    Set<UserBasicInfoBO> getBatchUserBasicInfoByUserIds(Set<Long> userIds);
}
