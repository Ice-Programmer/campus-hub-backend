package com.ice.campus.chat.controller;

import com.ice.campus.chat.model.dto.room.ChatRoomCreateRequest;
import com.ice.campus.chat.service.ChatRoomService;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/22 20:05
 */
@RestController
@Slf4j
@RequestMapping("/chat/room")
public class ChatRoomController {

    @Resource
    private ChatRoomService chatRoomService;

    /**
     * 创建聊天室
     *
     * @param createRequest 创建请求
     * @return 聊天室
     */
    @PostMapping("/create")
    public BaseResponse<Long> createChatRoom(@Valid @RequestBody ChatRoomCreateRequest createRequest) {
        if (createRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long roomId = chatRoomService.createChatRoom(createRequest);

        return ResultUtils.success(roomId);
    }
}
