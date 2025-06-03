package com.ice.campus.chat.manager;

import com.ice.campus.chat.model.event.ChatMessageEvent;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 19:49
 */
@Component
@Slf4j
public class ChatMessageEventManager {

    @Resource
    private Disruptor<ChatMessageEvent> messageEventDisruptor;

    @PreDestroy
    private void destroy() {
        if (messageEventDisruptor != null) {
            messageEventDisruptor.shutdown();
            log.info("ChatMessageEventManager 已关闭");
        }
    }

    /**
     * 发布事件到Disruptor
     *
     * @param event 聊天消息事件
     */
    public void publishEvent(ChatMessageEvent event) {
        RingBuffer<ChatMessageEvent> ringBuffer = messageEventDisruptor.getRingBuffer();
        if (ringBuffer != null) {
            // 获取下一个序列
            long sequence = ringBuffer.next();
            try {
                // 获取该序列对应的事件对象
                ChatMessageEvent existingEvent = ringBuffer.get(sequence);
                // 复制事件数据
                existingEvent.setEventType(event.getEventType());
                existingEvent.setRoomId(event.getRoomId());
                existingEvent.setUserInfo(event.getUserInfo());
                existingEvent.setContent(event.getContent());
                existingEvent.setContentType(event.getContentType());
                existingEvent.setReplyMessageId(event.getReplyMessageId());
                existingEvent.setMessageId(event.getMessageId());
                existingEvent.setTimestamp(event.getTimestamp());
            } finally {
                // 发布事件
                ringBuffer.publish(sequence);
            }
        }
    }


}
