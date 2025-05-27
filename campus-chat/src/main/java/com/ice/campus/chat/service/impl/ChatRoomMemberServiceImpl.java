package com.ice.campus.chat.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.chat.model.entity.ChatRoomMember;
import com.ice.campus.chat.service.ChatRoomMemberService;
import com.ice.campus.chat.mapper.ChatRoomMemberMapper;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import org.springframework.stereotype.Service;

/**
 * @author chenjiahan
 * @description 针对表【chat_room_member(聊天室表)】的数据库操作Service实现
 * @createDate 2025-05-22 18:18:26
 */
@Service
public class ChatRoomMemberServiceImpl extends ServiceImpl<ChatRoomMemberMapper, ChatRoomMember>
        implements ChatRoomMemberService {

    @Override
    public boolean isMember(UserBasicInfo currentUser, long roomId) {
        // todo 优化成从 redis 中获取
        return baseMapper.exists(Wrappers.<ChatRoomMember>lambdaQuery()
                .eq(ChatRoomMember::getRoomId, roomId)
                .eq(ChatRoomMember::getUserId, currentUser.getId()));
    }
}