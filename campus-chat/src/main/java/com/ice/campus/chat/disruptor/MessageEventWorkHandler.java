package com.ice.campus.chat.disruptor;

import com.ice.campus.chat.event.WebSocketMessageEvent;
import com.ice.campus.chat.model.dto.message.WebsocketMessageRequest;
import com.ice.campus.chat.model.entity.ChatMessage;
import com.ice.campus.chat.model.enums.ChatMessageTypeEnum;
import com.ice.campus.chat.model.event.ChatMessageEvent;
import com.ice.campus.chat.service.ChatMessageReadService;
import com.ice.campus.chat.service.ChatMessageService;
import com.lmax.disruptor.WorkHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 20:33
 */
@Component
@Slf4j
public class MessageEventWorkHandler implements WorkHandler<ChatMessageEvent> {

    @Resource
    private ApplicationEventPublisher eventPublisher;

    @Resource
    private ChatMessageService chatMessageService;

    @Resource
    private ChatMessageReadService chatMessageReadService;

    @Override
    public void onEvent(ChatMessageEvent chatMessageEvent) {
        try {
            // 根据事件类型处理
            switch (chatMessageEvent.getEventType()) {
                case USER_JOIN -> handleUserJoin(chatMessageEvent);
                case USER_LEAVE -> handleUserLeave(chatMessageEvent);
                case SEND_MESSAGE -> handleSendMessage(chatMessageEvent);
                case MESSAGE_READ -> handleMessageRead(chatMessageEvent);
                default -> log.warn("未知的事件类型: {}", chatMessageEvent.getEventType());
            }
        } catch (Exception e) {
            log.error("处理聊天消息事件失败", e);
        } finally {
            // 清空事件数据，便于对象复用
            chatMessageEvent.clear();
        }
    }

    /**
     * 处理用户加入事件
     */
    private void handleUserJoin(ChatMessageEvent event) {
        // 创建通知消息
        WebsocketMessageRequest messageDTO = new WebsocketMessageRequest();
        messageDTO.setType(ChatMessageTypeEnum.USER_JOIN);
        messageDTO.setRoomId(event.getRoomId());
        messageDTO.setSenderUserInfo(event.getUserInfo());
        messageDTO.setContent(String.format("用户 %s 进入聊天室", event.getUserInfo().getUsername()));
        messageDTO.setTimestamp(event.getTimestamp());

        // 发布WebSocket消息事件
        eventPublisher.publishEvent(new WebSocketMessageEvent(this, event.getRoomId(), messageDTO));
    }

    /**
     * 处理用户离开事件
     */
    private void handleUserLeave(ChatMessageEvent event) {
        // 创建通知消息
        WebsocketMessageRequest messageDTO = new WebsocketMessageRequest();
        messageDTO.setType(ChatMessageTypeEnum.USER_LEAVE);
        messageDTO.setRoomId(event.getRoomId());
        messageDTO.setSenderUserInfo(event.getUserInfo());
        messageDTO.setContent(String.format("用户 %s 离开聊天室", event.getUserInfo().getUsername()));
        messageDTO.setTimestamp(event.getTimestamp());

        // 发布WebSocket消息事件
        eventPublisher.publishEvent(new WebSocketMessageEvent(this, event.getRoomId(), messageDTO));
    }

    /**
     * 处理发送消息事件
     */
    private void handleSendMessage(ChatMessageEvent event) {
        // 保存消息到数据库
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(event.getRoomId());
        chatMessage.setUserId(event.getUserInfo().getId());
        chatMessage.setContentType(event.getContentType());
        chatMessage.setContent(event.getContent());
        chatMessage.setReplyMessageId(event.getReplyMessageId());
        chatMessageService.save(chatMessage);

        // 创建WebSocket消息
        WebsocketMessageRequest messageDTO = new WebsocketMessageRequest();
        messageDTO.setType(ChatMessageTypeEnum.CHAT_MESSAGE);
        messageDTO.setRoomId(event.getRoomId());
        messageDTO.setMessageId(chatMessage.getId()); // 使用保存后的消息ID
        messageDTO.setSenderUserInfo(event.getUserInfo());
        messageDTO.setContent(event.getContent());
        messageDTO.setContentType(event.getContentType());
        messageDTO.setReplyMessageId(event.getReplyMessageId());
        messageDTO.setTimestamp(event.getTimestamp());
        messageDTO.setReadUserIds(new ArrayList<>()); // 初始化已读用户列表

        // 发布WebSocket消息事件
        eventPublisher.publishEvent(new WebSocketMessageEvent(this, event.getRoomId(), messageDTO));
    }

    /**
     * 处理消息已读事件
     */
    private void handleMessageRead(ChatMessageEvent event) {
        // 标记消息为已读
        boolean success = chatMessageReadService.markMessageAsRead(
                event.getMessageId(),
                event.getUserInfo(),
                event.getRoomId()
        );

        if (success) {
            // 获取已读用户列表
            List<Long> readUserIds = chatMessageReadService.getReadUserIdsByMessageId(event.getMessageId());
            // 创建已读通知消息
            WebsocketMessageRequest messageDTO = new WebsocketMessageRequest();
            messageDTO.setType(ChatMessageTypeEnum.MESSAGE_READ);
            messageDTO.setRoomId(event.getRoomId());
            messageDTO.setMessageId(event.getMessageId());
            messageDTO.setSenderUserInfo(event.getUserInfo());
            messageDTO.setTimestamp(event.getTimestamp());
            messageDTO.setReadUserIds(readUserIds);

            // 发布WebSocket消息事件
            eventPublisher.publishEvent(new WebSocketMessageEvent(this, event.getRoomId(), messageDTO));

            log.info("用户「{}」已读消息「{}」", event.getUserInfo().getId(), event.getMessageId());
        } else {
            log.warn("标记消息[{}]为已读失败，用户[{}]", event.getMessageId(), event.getUserInfo().getId());
        }
    }

}
