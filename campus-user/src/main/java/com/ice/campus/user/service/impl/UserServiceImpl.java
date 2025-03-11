package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.cache.constant.CacheConstant;
import com.ice.campus.common.cache.manager.RedisManager;
import com.ice.campus.common.core.constant.DatabaseConstant;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.user.constant.UserConstant;
import com.ice.campus.user.mapper.UserMapper;
import com.ice.campus.user.model.entity.User;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.user.service.UserService;
import com.ice.campus.common.auth.constant.UserRoleConstant;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author chenjiahan
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-03-09 22:12:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private RedisManager redisManager;

    @Override
    public UserBasicInfo userLoginByMail(String email, String verifyCode) {
        // 1. 从缓存中判断验证码是否正确
        String emailHash = DigestUtils.md5DigestAsHex(email.getBytes());
        String verifyCodeCacheKey = CacheConstant.EMAIL_VERIFY_CODE_PREFIX + emailHash;
        String cacheVerifyCode = redisManager.get(verifyCodeCacheKey);
        ThrowUtils.throwIf(StringUtils.isBlank(cacheVerifyCode), ErrorCode.OPERATION_ERROR, "验证码已失效，请重新发送");
        if (!verifyCode.equals(cacheVerifyCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "验证码错误！");
        }

        // 2. 判断用户是否存在
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
                .select(User::getId, User::getUsername, User::getUserAvatar, User::getRole, User::getCreateTime)
                .last(DatabaseConstant.LIMIT_ONE));

        // 2.1 不存在新用户插入数据库
        if (ObjectUtils.isEmpty(user)) {
            user = new User();
            user.setEmail(email);
            user.setUsername(UserConstant.generateUniqueUsername());
            user.setUserAvatar(UserConstant.getRandomUserAvatar());
            try {
                baseMapper.insert(user);
            } catch (DuplicateKeyException e) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "请勿重复操作！");
            }
        }

        // 3. 判断用户是否被 ban
        if (UserRoleConstant.BAN.equals(user.getRole())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "用户当前已禁用，请联系管理员");
        }

        // 4. 填充基础信息
        UserBasicInfo userBasicInfo = new UserBasicInfo();
        BeanUtils.copyProperties(user, userBasicInfo);
        userBasicInfo.setEmail(email);
        return userBasicInfo;
    }
}




