package com.ice.campus.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.course.model.entity.CourseResource;
import com.ice.campus.course.service.CourseResourceService;
import com.ice.campus.course.mapper.CourseResourceMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【course_resource(课程资料表)】的数据库操作Service实现
* @createDate 2025-04-28 22:50:51
*/
@Service
public class CourseResourceServiceImpl extends ServiceImpl<CourseResourceMapper, CourseResource>
    implements CourseResourceService{

}




