package com.ice.campus.common.auth.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 拦截器相关常量
 */
public interface InterceptorConstant {

    /**
     * 拦截器拦截路径
     */
    List<String> AUTH_INTERCEPTOR_PATH_PATTERN = Collections.singletonList("/**");

    /**
     * 拦截器不拦截路径
     */
    List<String> AUTH_INTERCEPTOR_EXCLUDE_PATH_PATTERN = Arrays.asList(
            // 用户注册
            "/user/register/account",
            // 接口文档相关
            "/doc.html/**",
            "/favicon.ico/**",
            "/v3/api-docs/**",
            "/webjars/**",
            // 用户登录
            "/auth/login/password"
    );
}
