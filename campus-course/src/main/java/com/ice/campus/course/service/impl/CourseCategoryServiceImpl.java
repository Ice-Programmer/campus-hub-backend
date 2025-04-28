package com.ice.campus.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.course.model.entity.CourseCategory;
import com.ice.campus.course.service.CourseCategoryService;
import com.ice.campus.course.mapper.CourseCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【course_category(课程分类表)】的数据库操作Service实现
* @createDate 2025-04-28 22:50:51
*/
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory>
    implements CourseCategoryService{

}




