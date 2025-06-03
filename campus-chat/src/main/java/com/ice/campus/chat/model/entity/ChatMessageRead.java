package com.ice.campus.chat.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 聊天消息已读记录表
 * @TableName chat_message_read
 */
@TableName(value ="chat_message_read")
@Data
public class ChatMessageRead implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 聊天室ID
     */
    private Long roomId;

    /**
     * 已读时间
     */
    private Date readTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}