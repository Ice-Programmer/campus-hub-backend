package com.ice.campus.course.service;

import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.course.model.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.course.model.vo.CourseVO;

/**
* @author chenjiahan
* @description 针对表【course(课程基本信息表)】的数据库操作Service
* @createDate 2025-04-28 22:50:51
*/
public interface CourseService extends IService<Course> {

    BaseResponse<CourseVO> getCourseVOById(Long courseId);
}
