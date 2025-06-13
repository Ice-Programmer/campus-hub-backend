package com.ice.campus.team.mapper;

import com.ice.campus.team.model.entity.Team;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenjiahan
 * @description 针对表【team(队伍表)】的数据库操作Mapper
 * @createDate 2025-06-11 16:23:18
 * @Entity generator.model.entity.Team
 */
public interface TeamMapper extends BaseMapper<Team> {

    /**
     * 查询用户创建队伍数量
     *
     * @param userId 用户 id
     * @return 队伍创建数量
     */
    int countUserTeamNum(@Param("userId") Long userId);
}




