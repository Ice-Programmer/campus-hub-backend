package com.ice.campus.team.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 0-已退出，1-正常
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/12 19:28
 */
@Getter
public enum TeamMemberStatusEnum implements BaseEnum<Integer> {

    QUIT("已退出", 0),
    IN("正常", 1);


    private final String text;

    private final Integer value;

    TeamMemberStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
