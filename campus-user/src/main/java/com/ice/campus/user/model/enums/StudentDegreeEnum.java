package com.ice.campus.user.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 大学生学历枚举
 **/
@Getter
public enum StudentDegreeEnum implements BaseEnum<Integer> {

    JUNIOR_COLLEGE("专科", 0),
    UNDERGRADUATE("本科", 1),
    MASTER("研究生", 2),
    PHD("博士", 3);

    private final String text;

    private final Integer value;

    StudentDegreeEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
