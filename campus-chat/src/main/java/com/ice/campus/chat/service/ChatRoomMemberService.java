package com.ice.campus.chat.service;

import com.ice.campus.chat.model.entity.ChatRoomMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.common.auth.vo.UserBasicInfo;

/**
 * @author chenjiahan
 * @description 针对表【chat_room_member(聊天室表)】的数据库操作Service
 * @createDate 2025-05-22 18:18:26
 */
public interface ChatRoomMemberService extends IService<ChatRoomMember> {

    /**
     * 判断当前用户是否为聊天室成员
     *
     * @param currentUser 当前登陆用户
     * @param roomId      聊天室 id
     * @return 是否为聊天室成员
     */
    boolean isMember(UserBasicInfo currentUser, long roomId);
}
