package com.ice.campus.user.service.rpc;

import com.ice.campus.api.user.bo.UserBasicInfoBO;
import com.ice.campus.api.user.service.UserBasicRpcService;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessRpcException;
import com.ice.campus.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/14 11:52
 */
@DubboService
public class UserBasicRpcServiceImpl implements UserBasicRpcService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserBasicInfoBO getUserBasicInfoByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new BusinessRpcException(ErrorCode.PARAMS_ERROR, "用户 id 错误！");
        }
        return userMapper.selectUserBasicInfoByUserId(userId);
    }

    @Override
    public List<UserBasicInfoBO> getBatchUserBasicInfoByUserIds(List<Long> userIds) {
        return List.of();
    }
}
