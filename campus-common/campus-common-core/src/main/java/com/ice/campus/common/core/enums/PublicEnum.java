package com.ice.campus.common.core.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:32
 */
@Getter
public enum PublicEnum implements BaseEnum<Integer> {
    PRIVATE("私有", 0),
    PUBLIC("公开", 1);

    private final String text;

    private final Integer value;

    PublicEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
