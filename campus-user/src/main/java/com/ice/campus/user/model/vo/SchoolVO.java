package com.ice.campus.user.model.vo;

import com.ice.campus.user.model.entity.School;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 学校返回类
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/3 20:39
 */
@Data
public class SchoolVO {
    /**
     * id
     */
    private Long id;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 实体类转视图类
     */
    public static SchoolVO objToVO(School school) {
        SchoolVO schoolVO = new SchoolVO();
        BeanUtils.copyProperties(school, schoolVO);
        return schoolVO;
    }
}
