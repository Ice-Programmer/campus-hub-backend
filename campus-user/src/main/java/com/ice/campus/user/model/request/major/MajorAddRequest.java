package com.ice.campus.user.model.request.major;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 专业新增请求
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/3 21:06
 */
@Data
public class MajorAddRequest {

    /**
     * 专业名称
     */
    @Length(max = 30, message = "专业名称长度不能大于 30！")
    private String majorName;
}
