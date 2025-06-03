package com.ice.campus.chat.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.ice.campus.chat.disruptor.MessageEventWorkHandler;
import com.ice.campus.chat.model.event.ChatMessageEvent;
import com.lmax.disruptor.dsl.Disruptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 20:32
 */
@Configuration
@Slf4j
public class ChatMessageDisruptorConfig {
    @Resource
    private MessageEventWorkHandler messageEventWorkHandler;

    @Bean("messageEventDisruptor")
    public Disruptor<ChatMessageEvent> messageModelRingBuffer() {
        // 定义 ringBuffer 的大小
        int bufferSize = 1024 * 256;

        log.info("初始化聊天消息Disruptor，缓冲区大小: {}", bufferSize);


        // 创建 disruptor
        Disruptor<ChatMessageEvent> disruptor = new Disruptor<>(
                ChatMessageEvent::new,
                bufferSize,
                ThreadFactoryBuilder.create().setNamePrefix("chat-event-processor").build()
        );

        // 设置消费者
        disruptor.handleEventsWithWorkerPool(messageEventWorkHandler);
        // 启动 disruptor
        disruptor.start();
        return disruptor;
    }
}
