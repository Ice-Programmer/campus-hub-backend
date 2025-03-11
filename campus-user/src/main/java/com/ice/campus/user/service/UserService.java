package com.ice.campus.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.user.model.entity.User;
import com.ice.campus.common.auth.vo.UserBasicInfo;

/**
 * @author chenjiahan
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2025-03-09 22:12:38
 */
public interface UserService extends IService<User> {

    /**
     * 用户根据邮箱登录
     *
     * @param email      邮箱
     * @param verifyCode 验证码
     * @return 用户登录信息
     */
    UserBasicInfo userLoginByMail(String email, String verifyCode);
}
