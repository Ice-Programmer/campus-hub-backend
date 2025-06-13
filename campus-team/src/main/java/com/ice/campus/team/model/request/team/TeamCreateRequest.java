package com.ice.campus.team.model.request.team;

import com.ice.campus.common.core.enums.ApplyEnum;
import com.ice.campus.common.core.enums.PublicEnum;
import com.ice.campus.common.core.validation.annotation.EnumCheck;
import com.ice.campus.team.model.enums.TeamTypeEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 队伍创建 Request
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 21:19
 */
@Data
public class TeamCreateRequest implements Serializable {

    /**
     * 队伍名称
     */
    @NotBlank(message = "队伍名称长度不得为空")
    @Length(max = 30, message = "队伍名称长度不得超过 30 字！")
    private String teamName;

    /**
     * 队伍描述
     */
    @Length(max = 1000, message = "队伍介绍不得超过 1000 字！")
    private String description;

    /**
     * 最大成员数（默认最多 20 人）
     */
    @Max(value = 20, message = "队伍成员默认最多 20 人！")
    private Integer maxMembers = 20;

    /**
     * 队伍类型：1-学习小组，2-项目团队，3-竞赛队伍，4-社团组织，5-兴趣小组
     */
    @EnumCheck(enumClass = TeamTypeEnum.class, message = "队伍类型错误！")
    private Integer teamType;

    /**
     * 是否公开：0-私有，1-公开
     */
    @EnumCheck(enumClass = PublicEnum.class, message = "队伍是否可见错误！")
    private Integer isPublic;

    /**
     * 加入是否需要审批
     */
    @EnumCheck(enumClass = ApplyEnum.class, message = "设置审批类型错误！")
    private Integer isApply;

    /**
     * 标签（JSON格式）
     */
    @Size(max = 15, message = "最多创建 15 个标签")
    private List<String> tagList;

    @Serial
    private static final long serialVersionUID = 7114634565305106264L;
}
