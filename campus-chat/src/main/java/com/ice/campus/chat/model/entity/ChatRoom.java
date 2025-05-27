package com.ice.campus.chat.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 聊天室表
 * @TableName chat_room
 */
@TableName(value ="chat_room")
@Data
public class ChatRoom implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 聊天室名称
     */
    private String roomName;

    /**
     * 聊天室类型
     */
    private Integer roomType;

    /**
     * 创建用户 id
     */
    private Long createUserId;

    /**
     * 创建时间
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