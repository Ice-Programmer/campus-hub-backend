package com.ice.campus.team.model.request.team;

import com.ice.campus.common.core.common.PageRequest;
import com.ice.campus.common.core.enums.ApplyEnum;
import com.ice.campus.common.core.enums.PublicEnum;
import com.ice.campus.common.core.validation.annotation.EnumCheck;
import com.ice.campus.team.model.enums.TeamStatusEnum;
import com.ice.campus.team.model.enums.TeamTypeEnum;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/14 12:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TeamQueryRequest extends PageRequest {

    /**
     * id
     */
    private Long id;

    /**
     * id 列表
     */
    @Size(max = 100, message = "最多选择 100 个 id")
    private List<Long> ids;

    /**
     * 队伍名称
     */
    @Length(max = 100, message = "队伍名称最多不能超过 100 字")
    private String teamName;

    /**
     * tag 列表
     */
    @Length(max = 20, message = "标签名称不得长于 20 字")
    private String tag;

    /**
     * 过滤 id 列表
     */
    @Size(max = 100, message = "最多过滤 100 个用户")
    private List<Long> notId;

    /**
     * 是否需要申请
     */
    @EnumCheck(enumClass = ApplyEnum.class, message = "申请类型错误！", allowNull = true)
    private Integer isApply;

    /**
     * 队伍状态
     */
    @EnumCheck(enumClass = TeamStatusEnum.class, message = "队伍状态选择错误", allowNull = true)
    private Integer status;

    /**
     * 队伍类型
     */
    @EnumCheck(enumClass = TeamTypeEnum.class, message = "队伍类型错误！", allowNull = true)
    private Integer teamType;

    /**
     * 创建用户 id
     */
    private Long creatorId;
}
