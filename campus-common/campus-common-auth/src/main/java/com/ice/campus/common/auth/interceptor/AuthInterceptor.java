package com.ice.campus.common.auth.interceptor;

import com.ice.campus.common.auth.client.TokenClient;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/10 15:14
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private TokenClient tokenClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        log.info("拦截到 {}, 开始校验 token 信息", requestURI);
        // 获取 token 信息
        String accessToken = request.getHeader("Authorization");

        // 判断 token 是否有效
        UserBasicInfo userBasicInfoVO = tokenClient.checkTokenAndGetUserBasicInfo(accessToken);
        // 保存上下文
        SecurityContext.setCurrentUser(userBasicInfoVO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String requestURI = request.getRequestURI();
        log.info("请求 {} 业务已执行结束", requestURI);
        // 清楚上下文信息，防止上下文内容污染线程
        SecurityContext.clearAll();
    }
}
