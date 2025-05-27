package com.ice.campus.chat.model.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.Getter;

/**
 * 图片审核状态枚举类
 */
@Getter
public enum ChatRoomTypeEnum {

    NORMAL("普通房间", 0);

    private final String text;

    private final int value;

    ChatRoomTypeEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的 value
     * @return 枚举值
     */
    public static ChatRoomTypeEnum getEnumByValue(Integer value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (ChatRoomTypeEnum chatRoomTypeEnum : ChatRoomTypeEnum.values()) {
            if (chatRoomTypeEnum.value == value) {
                return chatRoomTypeEnum;
            }
        }
        return null;
    }
}
