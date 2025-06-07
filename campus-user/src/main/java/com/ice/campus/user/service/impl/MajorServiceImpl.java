package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.cache.annotation.CustomCache;
import com.ice.campus.common.cache.constant.CacheConstant;
import com.ice.campus.user.model.entity.Major;
import com.ice.campus.user.model.vo.MajorVO;
import com.ice.campus.user.service.MajorService;
import com.ice.campus.user.mapper.MajorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjiahan
 * @description 针对表【major(专业)】的数据库操作Service实现
 * @createDate 2025-06-03 20:36:01
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
        implements MajorService {

    @Override
    @CustomCache(key = CacheConstant.STUDENT_MAJOR_LIST_KEY, duration = CacheConstant.MONTH_EXPIRE_TIME)
    public List<MajorVO> getMajorList() {
        return this.list(Wrappers.<Major>lambdaQuery()
                        .select(Major::getId, Major::getMajorName)).stream()
                .map(MajorVO::objToVO)
                .toList();
    }
}




