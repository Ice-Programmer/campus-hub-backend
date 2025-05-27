package com.ice.campus.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.campus.chat.model.entity.ChatMessage;
import com.ice.campus.chat.service.ChatMessageService;
import com.ice.campus.chat.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;

/**
* @author chenjiahan
* @description 针对表【chat_message(聊天消息存储表)】的数据库操作Service实现
* @createDate 2025-05-22 18:18:26
*/
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage>
    implements ChatMessageService{

}




