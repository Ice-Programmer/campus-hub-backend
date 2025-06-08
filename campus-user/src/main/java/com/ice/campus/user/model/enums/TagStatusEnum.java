package com.ice.campus.user.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 标签状态枚举（0-待审核 1-已审核 2-已拒绝）
 **/
@Getter
public enum TagStatusEnum implements BaseEnum<Integer> {

    PENDING("待审核", 0),
    APPROVED("审核通过", 1),
    REJECTED("审核拒绝", 2);

    private final String text;

    private final Integer value;

    TagStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
