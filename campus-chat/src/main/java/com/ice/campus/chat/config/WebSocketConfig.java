//package com.ice.campus.chat.config;
//
//import com.ice.campus.chat.interceptor.WebSocketAuthInterceptor;
//import com.ice.campus.chat.interceptor.WebSocketSendInterceptor;
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
///**
// * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
// * @create 2025/5/22 18:37
// */
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//    @Resource
//    private WebSocketAuthInterceptor webSocketAuthInterceptor;
//
//    @Resource
//    private WebSocketSendInterceptor webSocketSendInterceptor;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//
////        registry.addHandler()
////                .addInterceptors(webSocketAuthInterceptor, webSocketSendInterceptor)
////                .setAllowedOrigins("*");
//    }
//}
