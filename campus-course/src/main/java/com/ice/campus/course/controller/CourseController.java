package com.ice.campus.course.controller;

import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.course.model.vo.CourseVO;
import com.ice.campus.course.service.CourseService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/4/28 22:55
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    /**
     * 根据课程 id 获取包装类
     *
     * @param courseId 课程 id
     * @return 课程包装类
     */
    @GetMapping("/{id}")
    public BaseResponse<CourseVO> getCourseVOById(@PathVariable("id") Long courseId) {
        if (courseId == null || courseId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return courseService.getCourseVOById(courseId);
    }
}
