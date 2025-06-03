package com.ice.campus.chat.model.enums;

/**
 * 消息类型枚举
 */
public enum ChatMessageTypeEnum {
    /**
     * 用户进入聊天室
     */
    USER_JOIN,

    /**
     * 用户离开聊天室
     */
    USER_LEAVE,

    /**
     * 聊天消息
     */
    CHAT_MESSAGE,

    /**
     * 消息已读通知
     */
    MESSAGE_READ,

    /**
     * 系统通知
     */
    SYSTEM_NOTICE
}