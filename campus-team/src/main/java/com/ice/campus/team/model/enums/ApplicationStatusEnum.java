package com.ice.campus.team.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 队伍成员申请状态：0-待审核，1-已通过，2-已拒绝
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/14 16:50
 */
@Getter
public enum ApplicationStatusEnum implements BaseEnum<Integer> {

    PENDING_REVIEW("待审核", 0),
    APPROVED("已通过", 1),
    REJECTED("已拒绝", 2);

    private final String text;

    private final Integer value;

    ApplicationStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
