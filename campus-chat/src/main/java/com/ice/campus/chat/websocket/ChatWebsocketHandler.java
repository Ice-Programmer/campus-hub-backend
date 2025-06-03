package com.ice.campus.chat.websocket;

import com.google.gson.Gson;
import com.ice.campus.chat.manager.ChatMessageEventManager;
import com.ice.campus.chat.model.dto.message.WebsocketMessageRequest;
import com.ice.campus.chat.model.enums.ChatEventTypeEnum;
import com.ice.campus.chat.model.event.ChatMessageEvent;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 18:39
 */
@Component
@Slf4j
public class ChatWebsocketHandler extends TextWebSocketHandler {

    /**
     * 存储所有在线用户的WebSocket会话
     * key: roomId:userId
     * value: WebSocketSession
     */
    private final Map<String, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 存储用户与聊天室的映射关系
     * key: userId
     * value: roomId
     */
    private final Map<Long, Long> USER_ROOM_MAP = new ConcurrentHashMap<>();

    @Resource
    private ChatMessageEventManager chatMessageEventManager;

    private static final Gson GSON = new Gson();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 获取用户信息
        UserBasicInfo currentUser = (UserBasicInfo) session.getAttributes().get(WebsocketConstant.CURRENT_USER_INFO_KEY);
        Long roomId = (Long) session.getAttributes().get(WebsocketConstant.ROOM_ID_KEY);

        Long userId = currentUser.getId();
        String sessionKey = getSessionKey(roomId, userId);

        // 存放用户信息
        SESSION_MAP.putIfAbsent(sessionKey, session);
        USER_ROOM_MAP.put(userId, roomId);

        log.info("用户「{}」连接到聊天室「{}」", currentUser.getUsername(), roomId);

        // 发布用户加入事件
        ChatMessageEvent event = new ChatMessageEvent();
        event.setEventType(ChatEventTypeEnum.USER_JOIN);
        event.setRoomId(roomId);
        event.setUserInfo(currentUser);
        event.setTimestamp(System.currentTimeMillis());
        chatMessageEventManager.publishEvent(event);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 获取用户信息和聊天室ID
        UserBasicInfo currentUser = (UserBasicInfo) session.getAttributes().get(WebsocketConstant.CURRENT_USER_INFO_KEY);
        Long roomId = (Long) session.getAttributes().get(WebsocketConstant.ROOM_ID_KEY);

        // 解析信息
        WebsocketMessageRequest messageRequest = GSON.fromJson(message.getPayload(), WebsocketMessageRequest.class);

        // 根据消息类型处理
        switch (messageRequest.getType()) {
            case CHAT_MESSAGE -> {
                // 发布发送消息事件
                ChatMessageEvent event = new ChatMessageEvent();
                event.setEventType(ChatEventTypeEnum.SEND_MESSAGE);
                event.setRoomId(roomId);
                event.setUserInfo(currentUser);
                event.setContent(messageRequest.getContent());
                event.setContentType(messageRequest.getContentType());
                event.setReplyMessageId(messageRequest.getReplyMessageId());
                event.setTimestamp(System.currentTimeMillis());
                chatMessageEventManager.publishEvent(event);
            }
            case MESSAGE_READ -> {
                // 发布消息已读事件
                ChatMessageEvent event = new ChatMessageEvent();
                event.setEventType(ChatEventTypeEnum.MESSAGE_READ);
                event.setRoomId(roomId);
                event.setUserInfo(currentUser);
                event.setMessageId(messageRequest.getMessageId());
                event.setTimestamp(System.currentTimeMillis());
                chatMessageEventManager.publishEvent(event);
            }
            default -> log.warn("未知的消息类型: {}", messageRequest.getType());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 获取用户信息和聊天室ID
        UserBasicInfo userInfo = (UserBasicInfo) session.getAttributes().get(WebsocketConstant.CURRENT_USER_INFO_KEY);
        Long roomId = (Long) session.getAttributes().get(WebsocketConstant.ROOM_ID_KEY);
        Long userId = userInfo.getId();
        String username = userInfo.getUsername();

        // 移除会话
        String sessionKey = getSessionKey(roomId, userId);
        SESSION_MAP.remove(sessionKey);
        USER_ROOM_MAP.remove(userId);

        log.info("用户[{}]断开与聊天室[{}]的连接", username, roomId);

        // 发布用户离开事件
        ChatMessageEvent event = new ChatMessageEvent();
        event.setEventType(ChatEventTypeEnum.USER_LEAVE);
        event.setRoomId(roomId);
        event.setUserInfo(userInfo);
        event.setTimestamp(System.currentTimeMillis());
        chatMessageEventManager.publishEvent(event);
    }

    /**
     * 发送消息给指定聊天室的所有用户
     *
     * @param roomId 聊天室ID
     * @param message 消息内容
     */
    public void sendMessageToRoom(Long roomId, WebsocketMessageRequest message) {
        try {
            String messageJson = GSON.toJson(message);
            TextMessage textMessage = new TextMessage(messageJson);

            // 获取聊天室所有成员
            for (Map.Entry<String, WebSocketSession> entry : SESSION_MAP.entrySet()) {
                String key = entry.getKey();
                // 检查是否是目标聊天室的会话
                if (key.startsWith(roomId + ":")) {
                    WebSocketSession targetSession = entry.getValue();
                    if (targetSession.isOpen()) {
                        targetSession.sendMessage(textMessage);
                    }
                }
            }
        } catch (IOException e) {
            log.error("发送消息到聊天室[{}]失败", roomId, e);
        }
    }

    /**
     * 获取会话键
     *
     * @param roomId 聊天室ID
     * @param userId 用户ID
     * @return 会话键
     */
    private String getSessionKey(Long roomId, Long userId) {
        return roomId + ":" + userId;
    }
}
