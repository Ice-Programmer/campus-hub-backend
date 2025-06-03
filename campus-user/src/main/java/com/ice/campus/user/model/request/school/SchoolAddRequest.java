package com.ice.campus.user.model.request.school;

import jakarta.validation.Valid;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 学校新增请求
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/3 21:06
 */
@Data
public class SchoolAddRequest {

    /**
     * 学校名称
     */
    @Length(max = 30, message = "学校名称长度不能大于 30！")
    private String schoolName;
}
