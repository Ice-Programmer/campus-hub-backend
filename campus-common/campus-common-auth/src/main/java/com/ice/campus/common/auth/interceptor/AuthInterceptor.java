package com.ice.campus.common.auth.interceptor;

import com.ice.campus.common.auth.annotation.IgnoreAuth;
import com.ice.campus.common.auth.client.TokenClient;
import com.ice.campus.common.auth.security.SecurityContext;
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.ThrowUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.annotation.Resource;

import java.lang.reflect.Method;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/10 15:14
 */
@Slf4j
@Component
@Order(0)
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private TokenClient tokenClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        Method method = handlerMethod.getMethod();

        // 判断是否忽略校验
        if (method.isAnnotationPresent(IgnoreAuth.class) || method.getDeclaringClass().isAnnotationPresent(IgnoreAuth.class)) {
            return true;
        }
        String requestURI = request.getRequestURI();
        log.info("拦截到 {}, 开始校验 token 信息", requestURI);
        // 获取 token 信息
        String accessToken = request.getHeader("Authorization");

        // 判断 token 是否有效
        UserBasicInfo userBasicInfoVO = tokenClient.checkTokenAndGetUserBasicInfo(accessToken);
        ThrowUtils.throwIf(userBasicInfoVO == null, ErrorCode.NOT_LOGIN_ERROR);
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
