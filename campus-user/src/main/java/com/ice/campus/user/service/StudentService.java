package com.ice.campus.user.service;

import com.ice.campus.user.model.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.user.model.request.student.StudentEditRequest;
import jakarta.validation.Valid;

/**
 * @author chenjiahan
 * @description 针对表【student(学生)】的数据库操作Service
 * @createDate 2025-06-03 20:36:01
 */
public interface StudentService extends IService<Student> {

    /**
     * 编辑学生基础信息
     *
     * @param studentEditRequest 学生编辑个人信息
     * @return 学生 id
     */
    Long editStudent(@Valid StudentEditRequest studentEditRequest);
}
