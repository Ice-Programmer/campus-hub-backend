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
public enum TeamCommonMemberEnum implements BaseEnum<Integer> {

    CREATOR("创建者", 1),
    MANAGER("管理员", 2),
    MEMBER("队员", 3);


    private final String text;

    private final Integer value;

    private TeamCommonMemberEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    public static Set<Integer> getCreatorAndManagerValueSet() {
        return Set.of(TeamCommonMemberEnum.CREATOR.getValue(), TeamCommonMemberEnum.MANAGER.getValue());
    }
}
