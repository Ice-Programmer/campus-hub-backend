package com.ice.campus.common.auth.enums;

import com.ice.campus.common.core.common.BaseEnum;
import lombok.Getter;

/**
 * 用户角色枚举
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/9 14:15
 */
@Getter
public enum UserRoleEnum implements BaseEnum<String> {

    ADMIN("管理员", "admin"),
    TEACHER("教师", "teacher"),
    STUDENT("学生", "student"),
    GUEST("访客", "guest"),
    EDITOR("编辑者", "editor"),
    BAN("禁用", "ban");


    private final String text;

    private final String value;

    UserRoleEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }
}
