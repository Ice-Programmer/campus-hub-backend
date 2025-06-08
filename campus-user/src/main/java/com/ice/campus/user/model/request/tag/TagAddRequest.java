package com.ice.campus.user.model.request.tag;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 标签新增请求
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/8 22:01
 */
@Data
public class TagAddRequest {

    /**
     * 标签长度
     */
    @Length(max = 15, message = "标签长度不得超过 15")
    private String tagName;
}
