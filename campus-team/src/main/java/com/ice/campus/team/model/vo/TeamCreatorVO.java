package com.ice.campus.team.model.vo;

import com.ice.campus.api.user.bo.UserBasicInfoBO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/13 22:15
 */
@Data
public class TeamCreatorVO {

    /**
     * 用户 id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 学校 id
     */
    private Long schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    public static TeamCreatorVO from(UserBasicInfoBO userBasicInfoBO) {
        TeamCreatorVO teamCreatorVO = new TeamCreatorVO();
        BeanUtils.copyProperties(userBasicInfoBO, teamCreatorVO);
        return teamCreatorVO;
    }
}
