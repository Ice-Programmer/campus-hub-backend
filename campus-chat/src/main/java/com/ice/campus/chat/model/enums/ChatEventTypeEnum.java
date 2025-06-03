package com.ice.campus.chat.model.enums;

/**
 * 事件类型枚举
 */
public enum ChatEventTypeEnum {
    /**
     * 用户进入聊天室
     */
    USER_JOIN,

    /**
     * 用户离开聊天室
     */
    USER_LEAVE,

    /**
     * 发送消息
     */
    SEND_MESSAGE,

    /**
     * 消息已读
     */
    MESSAGE_READ
}