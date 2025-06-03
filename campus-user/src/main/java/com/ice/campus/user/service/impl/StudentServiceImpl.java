package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.Student;
import com.ice.campus.user.service.StudentService;
import com.ice.campus.user.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【student(学生)】的数据库操作Service实现
* @createDate 2025-06-03 20:36:01
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




