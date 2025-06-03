package com.ice.campus.chat.model.event;

import com.ice.campus.chat.model.enums.ChatEventTypeEnum;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import lombok.Data;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 18:54
 */
@Data
public class ChatMessageEvent {

    /**
     * 事件类型
     */
    private ChatEventTypeEnum eventType;

    /**
     * 聊天室 id
     */
    private Long roomId;

    /**
     * 用户 id
     */
    private UserBasicInfo userInfo;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型
     */
    private String contentType;

    /**
     * 回复消息ID
     */
    private Long replyMessageId;

    /**
     * 消息 id（用于已读状态）
     */
    private Long messageId;

    /**
     * 时间戳
     */
    private Long timestamp;


    /**
     * 清空事件数据（用于对象复用）
     */
    public void clear() {
        this.eventType = null;
        this.roomId = null;
        this.userInfo = null;
        this.content = null;
        this.contentType = null;
        this.replyMessageId = null;
        this.messageId = null;
        this.timestamp = null;
    }
}
