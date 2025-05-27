package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.Teacher;
import com.ice.campus.user.service.TeacherService;
import com.ice.campus.user.mapper.TeacherMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【teacher(讲师信息表)】的数据库操作Service实现
* @createDate 2025-05-02 12:18:02
*/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService{

}




