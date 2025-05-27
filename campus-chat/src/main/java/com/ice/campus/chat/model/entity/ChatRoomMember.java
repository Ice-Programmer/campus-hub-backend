package com.ice.campus.chat.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 聊天室表
 * @TableName chat_room_member
 */
@TableName(value ="chat_room_member")
@Data
public class ChatRoomMember implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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