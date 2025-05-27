package com.ice.campus.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.chat.model.dto.room.ChatRoomCreateRequest;
import com.ice.campus.chat.model.entity.ChatRoom;
import com.ice.campus.chat.model.enums.ChatRoomTypeEnum;
import com.ice.campus.chat.service.ChatRoomService;
import com.ice.campus.chat.mapper.ChatRoomMapper;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.ThrowUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author chenjiahan
 * @description 针对表【chat_room(聊天室表)】的数据库操作Service实现
 * @createDate 2025-05-22 18:18:26
 */
@Service
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper, ChatRoom>
        implements ChatRoomService {

    @Override
    public Long createChatRoom(ChatRoomCreateRequest createRequest) {
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        ThrowUtils.throwIf(currentUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 1. 检验参数
        Integer roomType = createRequest.getRoomType();
        ChatRoomTypeEnum chatRoomTypeEnum = ChatRoomTypeEnum.getEnumByValue(roomType);
        ThrowUtils.throwIf(chatRoomTypeEnum == null, ErrorCode.PARAMS_ERROR, "房间类型不存在");

        // 2. todo 普通用户最多只能创建 5 个聊天室

        ChatRoom chatRoom = new ChatRoom();
        BeanUtils.copyProperties(createRequest, chatRoom);
        chatRoom.setCreateUserId(currentUser.getId());

        baseMapper.insert(chatRoom);
        return chatRoom.getId();
    }
}




