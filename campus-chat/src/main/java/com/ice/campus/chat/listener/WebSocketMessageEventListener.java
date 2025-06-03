package com.ice.campus.chat.listener;

import com.ice.campus.chat.event.WebSocketMessageEvent;
import com.ice.campus.chat.websocket.ChatWebsocketHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * WebSocket消息事件监听器
 * 监听WebSocketMessageEvent并调用ChatWebsocketHandler发送消息
 * 通过Spring事件机制解耦MessageEventWorkHandler和ChatWebsocketHandler的循环依赖
 * 
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 21:05
 */
@Component
@Slf4j
public class WebSocketMessageEventListener {
    
    @Resource
    private ChatWebsocketHandler chatWebsocketHandler;
    
    /**
     * 监听WebSocket消息事件
     * 异步处理以提高性能
     * 
     * @param event WebSocket消息事件
     */
    @EventListener
    public void handleWebSocketMessageEvent(WebSocketMessageEvent event) {
        try {
            log.debug("处理WebSocket消息事件，聊天室ID: {}, 消息类型: {}", 
                     event.getRoomId(), event.getMessage().getType());
            
            // 调用ChatWebsocketHandler发送消息
            chatWebsocketHandler.sendMessageToRoom(event.getRoomId(), event.getMessage());
            
        } catch (Exception e) {
            log.error("处理WebSocket消息事件失败，聊天室ID: {}", event.getRoomId(), e);
        }
    }
}