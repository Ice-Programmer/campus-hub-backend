package com.ice.campus.team.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.team.model.entity.TeamMember;
import com.ice.campus.team.service.TeamMemberService;
import com.ice.campus.team.mapper.TeamMemberMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【team_member(队伍成员表)】的数据库操作Service实现
* @createDate 2025-06-11 16:23:18
*/
@Service
public class TeamMemberServiceImpl extends ServiceImpl<TeamMemberMapper, TeamMember>
    implements TeamMemberService{

}




