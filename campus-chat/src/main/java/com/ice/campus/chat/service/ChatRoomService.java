package com.ice.campus.chat.service;

import com.ice.campus.chat.model.dto.room.ChatRoomCreateRequest;
import com.ice.campus.chat.model.entity.ChatRoom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.chat.model.vo.ChatRoomVO;

/**
 * @author chenjiahan
 * @description 针对表【chat_room(聊天室表)】的数据库操作Service
 * @createDate 2025-05-22 18:18:26
 */
public interface ChatRoomService extends IService<ChatRoom> {

    /**
     * 创建聊天室
     *
     * @param createRequest 创建请求
     * @return 聊天室
     */
    Long createChatRoom(ChatRoomCreateRequest createRequest);
}
