package com.ice.campus.team.model.request.role;

import com.ice.campus.common.core.enums.OfficialEnum;
import com.ice.campus.common.core.validation.annotation.EnumCheck;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 16:24
 */
@Data
public class MemberRoleAddRequest implements Serializable {

    /**
     * 队伍 id（可以关联队伍）
     */
    private Long teamId;

    /**
     * 介绍
     */
    @Length(max = 100, message = "队伍成员介绍长度不能超过 100!")
    private String description;

    /**
     * 是否官方(0-不是，1-是)
     */
    @EnumCheck(enumClass = OfficialEnum.class, message = "枚举类型错误！")
    private Integer isOfficial;

    /**
     * 角色名称
     */
    @Length(max = 20, message = "名称长度不能超过 20！")
    private String roleName;

    @Serial
    private static final long serialVersionUID = 1L;
}
