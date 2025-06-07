package com.ice.campus.user.service;

import com.ice.campus.user.model.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.user.model.vo.MajorVO;

import java.util.List;

/**
* @author chenjiahan
* @description 针对表【major(专业)】的数据库操作Service
* @createDate 2025-06-03 20:36:01
*/
public interface MajorService extends IService<Major> {

    /**
     * 获取专业列表
     *
     * @return 专业列表
     */
    List<MajorVO> getMajorList();
}
