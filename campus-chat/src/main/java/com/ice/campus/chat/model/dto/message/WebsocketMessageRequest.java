package com.ice.campus.chat.model.dto.message;

import com.ice.campus.chat.model.enums.ChatMessageTypeEnum;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 20:11
 */
@Data
public class WebsocketMessageRequest {
    /**
     * 消息类型
     */
    private ChatMessageTypeEnum type;

    /**
     * 聊天室ID
     */
    private Long roomId;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 发送者信息
     */
    private UserBasicInfo senderUserInfo;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 回复消息ID
     */
    private Long replyMessageId;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 已读用户ID列表
     */
    private List<Long> readUserIds;

}
