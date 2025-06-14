package com.ice.campus.team.model.request.team;

import lombok.Data;

/**
 * 解散队伍
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/14 18:26
 */
@Data
public class TeamDisbandRequest {

    /**
     * 队伍 id
     */
    private Long teamId;
}
