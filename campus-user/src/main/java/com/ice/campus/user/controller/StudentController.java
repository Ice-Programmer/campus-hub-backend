package com.ice.campus.user.controller;

import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.user.model.request.student.StudentEditRequest;
import com.ice.campus.user.service.StudentService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/4 11:48
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Resource
    private StudentService studentService;


    /**
     * 编辑学生基础信息
     *
     * @param studentEditRequest 学生编辑个人信息
     * @return 学生 id
     */
    @PostMapping("/edit")
    public BaseResponse<Long> editStudent(@RequestBody @Valid StudentEditRequest studentEditRequest) {
        return ResultUtils.success(studentService.editStudent(studentEditRequest));
    }
}
