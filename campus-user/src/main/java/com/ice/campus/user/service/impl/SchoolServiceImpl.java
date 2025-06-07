package com.ice.campus.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.common.cache.annotation.CustomCache;
import com.ice.campus.common.cache.constant.CacheConstant;
import com.ice.campus.user.model.entity.School;
import com.ice.campus.user.model.vo.SchoolVO;
import com.ice.campus.user.service.SchoolService;
import com.ice.campus.user.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjiahan
 * @description 针对表【school(学校)】的数据库操作Service实现
 * @createDate 2025-06-03 20:36:01
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
        implements SchoolService {

    @Override
    @CustomCache(key = CacheConstant.STUDENT_SCHOOL_LIST_KEY, duration = CacheConstant.MONTH_EXPIRE_TIME)
    public List<SchoolVO> getSchoolList() {
        return this.list(Wrappers.<School>lambdaQuery()
                        .select(School::getId, School::getSchoolName)).stream()
                .map(SchoolVO::objToVO)
                .toList();
    }
}




