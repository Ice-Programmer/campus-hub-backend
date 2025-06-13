package com.ice.campus.common.core.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 是否需要审批（0-不需要，1-需要）
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:34
 */
@Getter
public enum ApplyEnum implements BaseEnum<Integer> {
    NO_APPLY("不需要审批", 0),
    IS_APPLY("需要审批", 1);

    private final String text;

    private final Integer value;

    ApplyEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
