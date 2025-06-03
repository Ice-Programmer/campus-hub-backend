package com.ice.campus.user.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ice.campus.common.auth.annotation.IgnoreAuth;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.DeleteRequest;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.user.model.entity.Major;
import com.ice.campus.user.model.request.major.MajorAddRequest;
import com.ice.campus.user.model.vo.MajorVO;
import com.ice.campus.user.service.MajorService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专业管理接口
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/3 20:37
 */
@RestController
@RequestMapping("/major")
@Slf4j
public class MajorController {

    @Resource
    private MajorService majorService;

    /**
     * 获取专业列表
     *
     * @return 专业列表
     */
    @IgnoreAuth
    @GetMapping("/list")
    public BaseResponse<List<MajorVO>> getMajorList() {
        List<MajorVO> majorVOList = majorService.list(Wrappers.<Major>lambdaQuery()
                        .select(Major::getId, Major::getMajorName)).stream()
                .map(MajorVO::objToVO)
                .toList();
        return ResultUtils.success(majorVOList);
    }

    /**
     * 添加专业列表
     *
     * @param majorAddRequest 专业名称
     * @return 专业 id
     */
    @PostMapping("/add")
    public BaseResponse<Long> addMajor(@Valid @RequestBody MajorAddRequest majorAddRequest) {
        if (majorAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Major major = new Major();
        major.setMajorName(majorAddRequest.getMajorName());
        majorService.save(major);
        return ResultUtils.success(major.getId());
    }

    /**
     * 删除专业 id
     *
     * @param deleteRequest 删除请求
     * @return 删除成功
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteMajor(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ThrowUtils.throwIf(deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR, "id 不能小于 0");
        boolean result = majorService.remove(Wrappers.<Major>lambdaQuery()
                .eq(Major::getId, deleteRequest.getId()));
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR, String.format("专业「%s」删除失败", deleteRequest.getId()));
        return ResultUtils.success(true);
    }
}
