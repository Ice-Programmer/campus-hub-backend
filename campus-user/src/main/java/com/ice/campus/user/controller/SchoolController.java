package com.ice.campus.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ice.campus.common.auth.annotation.IgnoreAuth;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.DeleteRequest;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.user.model.entity.School;
import com.ice.campus.user.model.request.school.SchoolAddRequest;
import com.ice.campus.user.model.vo.SchoolVO;
import com.ice.campus.user.service.SchoolService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学校管理接口
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/3 20:37
 */
@RestController
@RequestMapping("/school")
@Slf4j
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    /**
     * 获取学校列表
     *
     * @return 学校列表
     */
    @IgnoreAuth
    @GetMapping("/list")
    public BaseResponse<List<SchoolVO>> getSchoolList() {
        List<SchoolVO> schoolVOList = schoolService.list(Wrappers.<School>lambdaQuery()
                        .select(School::getId, School::getSchoolName)).stream()
                .map(SchoolVO::objToVO)
                .toList();
        return ResultUtils.success(schoolVOList);
    }

    /**
     * 添加学校列表
     *
     * @param schoolAddRequest 学校名称
     * @return 学校 id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addSchool(@Valid @RequestBody SchoolAddRequest schoolAddRequest) {
        if (schoolAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        School school = new School();
        school.setSchoolName(schoolAddRequest.getSchoolName());
        schoolService.save(school);
        return ResultUtils.success(school.getId());
    }

    /**
     * 删除学校 id
     *
     * @param deleteRequest 删除请求
     * @return 删除成功
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteSchool(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ThrowUtils.throwIf(deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR, "id 不能小于 0");
        boolean result = schoolService.remove(Wrappers.<School>lambdaQuery()
                .eq(School::getId, deleteRequest.getId()));
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, String.format("学校「%s」删除失败", deleteRequest.getId()));
        return ResultUtils.success(true);
    }
}
