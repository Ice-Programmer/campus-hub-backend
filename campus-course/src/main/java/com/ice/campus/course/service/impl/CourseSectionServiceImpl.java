package com.ice.campus.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.course.model.entity.CourseSection;
import com.ice.campus.course.service.CourseSectionService;
import com.ice.campus.course.mapper.CourseSectionMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【course_section(课程内容表（小节）)】的数据库操作Service实现
* @createDate 2025-04-28 22:50:51
*/
@Service
public class CourseSectionServiceImpl extends ServiceImpl<CourseSectionMapper, CourseSection>
    implements CourseSectionService{

}




