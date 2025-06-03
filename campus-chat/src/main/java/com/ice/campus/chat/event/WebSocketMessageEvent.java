package com.ice.campus.chat.event;

import com.ice.campus.chat.model.dto.message.WebsocketMessageRequest;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * WebSocket消息事件
 * 用于解耦MessageEventWorkHandler和ChatWebsocketHandler之间的循环依赖
 * 
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 21:00
 */
@Getter
public class WebSocketMessageEvent extends ApplicationEvent {
    
    /**
     * 聊天室ID
     */
    private final Long roomId;
    
    /**
     * WebSocket消息内容
     */
    private final WebsocketMessageRequest message;
    
    public WebSocketMessageEvent(Object source, Long roomId, WebsocketMessageRequest message) {
        super(source);
        this.roomId = roomId;
        this.message = message;
    }
}