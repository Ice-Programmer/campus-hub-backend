package com.ice.campus.team.model.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 队伍类型枚举 1-学习小组，2-项目团队，3-竞赛队伍，4-社团组织，5-兴趣小组
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:27
 */
@Getter
public enum TeamTypeEnum implements BaseEnum<Integer> {

    STUDY_TEAM("学习小组", 1),
    PROJECT_TEAM("项目团队", 2),
    COMPETITION_TEAM("竞赛组织", 3),
    CLUB_TEAM("社团组织", 4),
    INTEREST_TEAM("兴趣小组", 5);

    private final String text;

    private final Integer value;

    TeamTypeEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
