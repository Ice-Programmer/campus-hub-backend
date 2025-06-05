package com.ice.campus.user.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 学生状态枚举（1-在读 2-休学 3-毕业 4-退学）
 **/
@Getter
public enum StudentStatusEnum implements BaseEnum<Integer> {

    ENROLLED("在读", 1),
    SUSPENDED("休学", 2),
    GRADUATED("毕业", 3),
    DROPPED_OUT("退学", 4);

    private final String text;

    private final Integer value;

    StudentStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
