package com.ice.campus.user.service;

import com.ice.campus.user.model.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.user.model.vo.SchoolVO;

import java.util.List;

/**
 * @author chenjiahan
 * @description 针对表【school(学校)】的数据库操作Service
 * @createDate 2025-06-03 20:36:01
 */
public interface SchoolService extends IService<School> {

    /**
     * 获取学校列表
     *
     * @return 返回学校列表
     */
    List<SchoolVO> getSchoolList();
}
