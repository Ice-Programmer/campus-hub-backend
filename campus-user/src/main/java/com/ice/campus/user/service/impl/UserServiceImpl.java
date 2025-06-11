package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.auth.enums.UserRoleEnum;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.cache.constant.CacheConstant;
import com.ice.campus.common.cache.manager.RedisManager;
import com.ice.campus.common.core.constant.DatabaseConstant;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.user.constant.UserConstant;
import com.ice.campus.user.mapper.RoleMapper;
import com.ice.campus.user.mapper.UserMapper;
import com.ice.campus.user.mapper.UserRoleMapper;
import com.ice.campus.user.model.entity.Role;
import com.ice.campus.user.model.entity.User;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.user.model.entity.UserRole;
import com.ice.campus.user.service.UserRoleService;
import com.ice.campus.user.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.DigestUtils;

import jakarta.annotation.Resource;

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

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public UserBasicInfo userLoginByMail(String email, String verifyCode) {
        // 1. 校验邮箱验证码是否正确
        verifyEmailCode(email, verifyCode);

        // 2. 判断用户是否存在
        UserBasicInfo userBasicInfo = baseMapper.getUserBasicInfoByEmail(email);

        // 2.1 不存在新用户插入数据库
        if (ObjectUtils.isEmpty(userBasicInfo)) {
            userBasicInfo = registerUser(email);
        }

        // 3. 判断用户身份是否
        if (UserRoleEnum.BAN.getValue().equals(userBasicInfo.getRole())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "用户当前已禁用，请联系管理员");
        }

        // 4. 填充基础信息
        return userBasicInfo;
    }

    /**
     * 注册新用户信息（事务方法）
     *
     * @param email 邮箱
     * @return 新用户信息
     */
    private UserBasicInfo registerUser(String email) {
        transactionTemplate.executeWithoutResult(status -> {
            try {
                // todo 线程安全问题 事务
                User user = new User();
                user.setEmail(email);
                user.setUsername(UserConstant.generateUniqueUsername());
                user.setUserAvatar(UserConstant.getRandomUserAvatar());
                baseMapper.insert(user);
                // 插入用户默认权限（guest）
                Role gusetRole = roleMapper.selectOne(Wrappers.<Role>lambdaQuery()
                        .eq(Role::getRoleCode, UserRoleEnum.GUEST.getValue())
                        .select(Role::getId)
                        .last(DatabaseConstant.LIMIT_ONE));
                ThrowUtils.throwIf(ObjectUtils.isEmpty(gusetRole), ErrorCode.NOT_FOUND_ERROR, "访客身份 id 不存在");
                UserRole userRole = new UserRole();
                userRole.setRoleId(gusetRole.getId());
                userRole.setUserId(user.getId());
                userRoleMapper.insert(userRole);
            } catch (DuplicateKeyException e) {
                status.setRollbackOnly();
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "该邮箱已注册");
            } catch (Exception e) {
                status.setRollbackOnly();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "用户注册失败, " + e.getMessage());
            }
        });

        // 返回完整用户信息
        return baseMapper.getUserBasicInfoByEmail(email);
    }

    /**
     * 校验当前邮箱验证码是否错误
     *
     * @param email      邮箱
     * @param verifyCode 验证码
     */
    private void verifyEmailCode(String email, String verifyCode) {
        // 1. 从缓存中判断验证码是否正确
        String emailHash = DigestUtils.md5DigestAsHex(email.getBytes());
        String verifyCodeCacheKey = CacheConstant.EMAIL_VERIFY_CODE_PREFIX + emailHash;
        String cacheVerifyCode = redisManager.get(verifyCodeCacheKey);
        ThrowUtils.throwIf(StringUtils.isBlank(cacheVerifyCode), ErrorCode.OPERATION_ERROR, "验证码已失效，请重新发送");
        if (!verifyCode.equals(cacheVerifyCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "验证码错误！");
        }
    }
}




