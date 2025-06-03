package com.ice.campus.chat.service;

import com.ice.campus.chat.model.entity.ChatMessageRead;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.campus.common.auth.vo.UserBasicInfo;

import java.util.List;

/**
 * @author chenjiahan
 * @description 针对表【chat_message_read(聊天消息已读记录表)】的数据库操作Service
 * @createDate 2025-05-27 20:56:32
 */
public interface ChatMessageReadService extends IService<ChatMessageRead> {

    /**
     * 标记用户已读
     *
     * @param messageId 消息 id
     * @param userInfo  用户信息
     * @param roomId    房间 id
     * @return 标记成功
     */
    boolean markMessageAsRead(Long messageId, UserBasicInfo userInfo, Long roomId);

    /**
     * 获取已读用户 id
     *
     * @param messageId 消息 id
     * @return 已读用户 id 列表
     */
    List<Long> getReadUserIdsByMessageId(Long messageId);
}
