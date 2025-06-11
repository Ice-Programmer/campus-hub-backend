package com.ice.campus.common.core.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 16:53
 */
@Getter
public enum OfficialEnum implements BaseEnum<Integer> {

    UNOFFICIAL("非官方", 0),
    OFFICIAL("官方", 1);

    private final String text;

    private final Integer value;

    OfficialEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
