package com.ice.campus.team.model.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ice.campus.team.model.entity.Team;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 队伍信息 VO
 */
@Data
public class TeamVO implements Serializable {

    private static final Gson GSON = new Gson();

    /**
     * id
     */
    private Long id;

    /**
     * 队伍名称
     */
    private String teamName;

    /**
     * 队伍描述
     */
    private String description;

    /**
     * 最大成员数
     */
    private Integer maxMembers;

    /**
     * 当前成员数
     */
    private Integer currentMembers;

    /**
     * 队伍类型：1-学习小组，2-项目团队，3-竞赛队伍，4-社团组织，5-兴趣小组
     */
    private Integer teamType;

    /**
     * 状态：0-审批中，1-招募中，2-活动中，3-停止招募，4-封禁中，5-审批失败
     */
    private Integer status;

    /**
     * 是否公开：0-私有，1-公开
     */
    private Integer isPublic;

    /**
     * 加入是否需要审批（0-不需要，1-需要）
     */
    private Integer isApply;

    /**
     * 标签（JSON格式）
     */
    private List<String> tags;

    /**
     * 队伍创建者基础信息
     */
    private TeamCreatorVO creator;

    /**
     * 是否加入
     */
    private Boolean hasJoin;

    @Serial
    private static final long serialVersionUID = 1L;

    public static TeamVO objToVO(Team team) {
        if (team == null) {
            return null;
        }
        TeamVO teamVO = new TeamVO();
        BeanUtils.copyProperties(team, teamVO);
        teamVO.setTags(GSON.fromJson(team.getTags(), new TypeToken<List<String>>() {
        }.getType()));
        return teamVO;
    }
}