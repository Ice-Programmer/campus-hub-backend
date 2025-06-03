package com.ice.campus.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.chat.model.entity.ChatMessageRead;
import com.ice.campus.chat.service.ChatMessageReadService;
import com.ice.campus.chat.mapper.ChatMessageReadMapper;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author chenjiahan
* @description 针对表【chat_message_read(聊天消息已读记录表)】的数据库操作Service实现
* @createDate 2025-05-27 20:56:32
*/
@Service
public class ChatMessageReadServiceImpl extends ServiceImpl<ChatMessageReadMapper, ChatMessageRead>
    implements ChatMessageReadService{

    @Override
    public boolean markMessageAsRead(Long messageId, UserBasicInfo userInfo, Long roomId) {

        return false;
    }

    @Override
    public List<Long> getReadUserIdsByMessageId(Long messageId) {

        return List.of();
    }
}




