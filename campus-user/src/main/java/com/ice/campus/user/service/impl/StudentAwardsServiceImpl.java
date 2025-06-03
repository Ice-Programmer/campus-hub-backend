package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.user.model.entity.StudentAwards;
import com.ice.campus.user.service.StudentAwardsService;
import com.ice.campus.user.mapper.StudentAwardsMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【student_awards(学生经历)】的数据库操作Service实现
* @createDate 2025-06-03 20:36:01
*/
@Service
public class StudentAwardsServiceImpl extends ServiceImpl<StudentAwardsMapper, StudentAwards>
    implements StudentAwardsService{

}




