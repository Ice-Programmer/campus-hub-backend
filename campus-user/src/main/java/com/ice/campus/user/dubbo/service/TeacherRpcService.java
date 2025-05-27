package com.ice.campus.user.dubbo.service;

import com.ice.campus.user.api.DubboTeacherRpcClientTriple;
import com.ice.campus.user.api.TeacherBasicInfo;
import com.ice.campus.user.api.TeacherBasicInfoRequest;
import com.ice.campus.user.mapper.TeacherMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/2 12:23
 */
@DubboService
@Slf4j
public class TeacherRpcService extends DubboTeacherRpcClientTriple.TeacherRpcClientImplBase {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public TeacherBasicInfo getTeacherBasicInfoByUserId(TeacherBasicInfoRequest request) {
//        log.info("get teacher basic info by userId {} from remote", request.getUserId());
//        TeacherBasicInfo teacherBasicInfo = teacherMapper.getTeacherBasicInfoByUserId();
        return null;
    }
}
