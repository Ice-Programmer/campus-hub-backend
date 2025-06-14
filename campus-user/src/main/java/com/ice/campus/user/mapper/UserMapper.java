package com.ice.campus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.campus.api.user.bo.UserBasicInfoBO;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenjiahan
 * @description 针对表【user(用户)】的数据库操作Mapper
 * @createDate 2025-03-09 22:12:38
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据邮箱获取用户信息
     *
     * @param email 邮箱
     * @return 用户信息
     */
    UserBasicInfo getUserBasicInfoByEmail(@Param("email") String email);


    /**
     * 查询用户基础信息
     *
     * @param userId 用户 id
     * @return 用户基础信息
     */
    UserBasicInfoBO selectUserBasicInfoByUserId(@Param("userId") long userId);
}