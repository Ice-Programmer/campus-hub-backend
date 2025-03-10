package com.ice.campus.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.campus.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author chenjiahan
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2025-03-09 22:12:38
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}




