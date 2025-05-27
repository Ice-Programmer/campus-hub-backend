package com.ice.campus.chat.model.vo;


import com.ice.campus.chat.model.entity.ChatRoom;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 聊天室 VO
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 15:58
 */
@Data
public class ChatRoomVO {
    /**
     * id
     */
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


    public static ChatRoomVO objToVO(ChatRoom chatRoom) {
        if (chatRoom == null) {
            return null;
        }
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        BeanUtils.copyProperties(chatRoom, chatRoomVO);
        return chatRoomVO;
    }
}
