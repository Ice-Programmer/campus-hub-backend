package com.ice.campus.team.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

import java.util.Set;

/**
 * 常用队伍成员枚举
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:00
 */
@Getter
public enum TeamCommonMemberEnum implements BaseEnum<Long> {

    CREATOR("创建者", 1L),
    MANAGER("管理员", 2L),
    MEMBER("队员", 3L);

    private final String text;

    private final Long value;

    TeamCommonMemberEnum(String text, Long value) {
        this.text = text;
        this.value = value;
    }

    public static Set<Long> getCreatorAndManagerValueSet() {
        return Set.of(TeamCommonMemberEnum.CREATOR.getValue(), TeamCommonMemberEnum.MANAGER.getValue());
    }
}
