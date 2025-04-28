package com.ice.campus.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.course.model.entity.UserCourse;
import com.ice.campus.course.service.UserCourseService;
import com.ice.campus.course.mapper.UserCourseMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【user_course(用户选课表)】的数据库操作Service实现
* @createDate 2025-04-28 22:50:51
*/
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse>
    implements UserCourseService{

}




