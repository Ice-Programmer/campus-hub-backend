package com.ice.campus.user.model.vo;

import com.ice.campus.user.model.entity.Major;
import com.ice.campus.user.model.entity.School;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 专业返回类
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/3 20:39
 */
@Data
public class MajorVO {
    /**
     * id
     */
    private Long id;

    /**
     * 学校名称
     */
    private String majorName;

    /**
     * 实体类转视图类
     */
    public static MajorVO objToVO(Major major) {
        MajorVO majorVO = new MajorVO();
        BeanUtils.copyProperties(major, majorVO);
        return majorVO;
    }
}
