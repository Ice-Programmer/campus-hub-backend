package com.ice.campus.course.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.course.model.entity.Course;
import com.ice.campus.course.model.vo.CourseVO;
import com.ice.campus.course.service.CourseService;
import com.ice.campus.course.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【course(课程基本信息表)】的数据库操作Service实现
* @createDate 2025-04-28 22:50:51
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

    @Override
    public BaseResponse<CourseVO> getCourseVOById(Long courseId) {

        return null;
    }
}




