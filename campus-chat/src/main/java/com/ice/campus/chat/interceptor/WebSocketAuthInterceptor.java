package com.ice.campus.chat.interceptor;

import com.ice.campus.chat.service.ChatRoomMemberService;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/5/22 18:38
 */
@Slf4j
@Component
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

    @Resource
    private ChatRoomMemberService chatRoomMemberService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            // 从请求中获取参数
            String roomIdStr = httpServletRequest.getParameter("roomId");
            if (StringUtils.isBlank(roomIdStr)) {
                log.error("房间号缺失，拒绝握手");
                return false;
            }
            long roomId = Long.parseLong(roomIdStr);
            UserBasicInfo currentUser = SecurityContext.getCurrentUser();
            if (currentUser == null) {
                log.error("用户未登录，拒绝握手");
                return false;
            }
            // todo 判断当前聊天室状态
            // 判断当前用户是否是聊天室成员
            if (!chatRoomMemberService.isMember(currentUser, roomId)) {
                log.error("当前用户不属于该聊天室，拒绝握手");
                return false;
            }
            // 设置用户登录信息等属性到 WebSocket 会话中
            attributes.put("currentUser", currentUser);
            attributes.put("userId", currentUser.getId());
            attributes.put("roomId", roomId);
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}

