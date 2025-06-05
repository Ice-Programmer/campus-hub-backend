package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.core.constant.DatabaseConstant;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.user.mapper.MajorMapper;
import com.ice.campus.user.mapper.SchoolMapper;
import com.ice.campus.user.model.entity.Major;
import com.ice.campus.user.model.entity.School;
import com.ice.campus.user.model.entity.Student;
import com.ice.campus.user.model.request.student.StudentEditRequest;
import com.ice.campus.user.service.StudentService;
import com.ice.campus.user.mapper.StudentMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author chenjiahan
 * @description 针对表【student(学生)】的数据库操作Service实现
 * @createDate 2025-06-03 20:36:01
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Resource
    private SchoolMapper schoolMapper;

    @Resource
    private MajorMapper majorMapper;

    @Override
    public Long editStudent(StudentEditRequest studentEditRequest) {
        Student student = new Student();
        BeanUtils.copyProperties(studentEditRequest, student);

        // 校验参数
        validStudent(student);

        // 判断新增还是修改
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        student.setUserId(currentUser.getId());

        // 判断是否存在
        Optional.ofNullable(baseMapper.selectOne(Wrappers.<Student>lambdaQuery()
                        .eq(Student::getUserId, currentUser.getId())
                        .last(DatabaseConstant.LIMIT_ONE)))
                .ifPresent(e -> student.setId(e.getId()));

        boolean result = this.saveOrUpdate(student);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, "更新学生信息失败，请重试！");
        return student.getId();
    }

    /**
     * 校验学生参数
     *
     * @param student 学生信息
     */
    private void validStudent(Student student) {
        Long schoolId = student.getSchoolId();
        Long majorId = student.getMajorId();
        Integer enrollmentYear = student.getEnrollmentYear();
        Integer graduationYear = student.getGraduationYear();

        // 校验学校 id
        boolean studentExist = schoolMapper.exists(Wrappers.<School>lambdaQuery()
                .eq(School::getId, schoolId));
        ThrowUtils.throwIf(!studentExist, ErrorCode.PARAMS_ERROR, "学校 id 错误！");

        // 校验专业 id
        boolean majorExist = majorMapper.exists(Wrappers.<Major>lambdaQuery()
                .eq(Major::getId, majorId));
        ThrowUtils.throwIf(!majorExist, ErrorCode.PARAMS_ERROR, "专业 id 错误！");

        if (graduationYear < enrollmentYear) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "毕业年份不得晚于入学年份！");
        }
    }
}




