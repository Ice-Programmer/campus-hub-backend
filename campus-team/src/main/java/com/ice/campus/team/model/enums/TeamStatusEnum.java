package com.ice.campus.team.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

import java.util.Set;

/**
 * 常用队伍状态枚举（0-审批中，1-招募中，2-活动中，3-停止招募，4-封禁中，5-审批失败）
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:00
 */
@Getter
public enum TeamStatusEnum implements BaseEnum<Integer> {

    UNDER_REVIEW("审批中", 0),
    RECRUITING("招募中", 1),
    IN_PROGRESS("活动中", 2),
    STOP_RECRUITING("停止招募", 3),
    BANNED("封禁中", 4),
    REVIEW_FAILED("审批失败", 5);

    private final String text;

    private final Integer value;

    TeamStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
