package com.ice.campus.chat.model.dto.room;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/27 16:00
 */
@Data
public class ChatRoomCreateRequest {

    /**
     * 聊天室名称
//     */
    @Length(min = 1, max = 30, message = "聊天室名称长度不得小于 1，大于 30！")
    private String roomName;

    /**
     * 聊天室类型
     */
    private Integer roomType;
}
