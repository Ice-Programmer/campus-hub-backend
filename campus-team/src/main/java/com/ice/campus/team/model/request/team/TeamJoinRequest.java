package com.ice.campus.team.model.request.team;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 加入队伍请求
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/14 15:24
 */
@Data
public class TeamJoinRequest {

    /**
     * 队伍 id
     */
    private Long teamId;

    /**
     * 加入消息
     */
    @Length(max = 150, message = "申请消息不能超过 150 字")
    private String message;
}
