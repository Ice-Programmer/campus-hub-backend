package com.ice.campus.user.mapper;

import com.ice.campus.user.api.TeacherBasicInfo;
import com.ice.campus.user.model.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author chenjiahan
* @description 针对表【teacher(讲师信息表)】的数据库操作Mapper
* @createDate 2025-05-02 12:18:02
* @Entity com.ice.campus.user.model.entity.Teacher
*/
public interface TeacherMapper extends BaseMapper<Teacher> {

    TeacherBasicInfo getTeacherBasicInfoByUserId();
}




