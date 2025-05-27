package com.ice.campus.chat.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 聊天消息存储表
 * @TableName chat_message
 */
@TableName(value ="chat_message")
@Data
public class ChatMessage implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 聊天室 id
     */
    private Long roomId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 内容类型(text, image, file, emoji)
     */
    private String contentType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 回复消息 id
     */
    private Long replyMessageId;

    /**
     * 创建时间（加入时间）
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    @Serial
    private static final long serialVersionUID = 1L;
}