package com.ice.campus.common.cache.constant;

/**
 * 第三方服务前缀
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 22:38
 */
public interface BizConstant {

    /**
     * 第三方服务 相关key
     */
    String BIZ_PREFIX = "campus_biz:";

    /**
     * 邮箱验证是否发送 key
     */
    String EMAIL_VERIFY_SEND_PREFIX = BIZ_PREFIX + "auth:email:verify:send:";

    /**
     * 邮箱验证码 key
     */
    String EMAIL_VERIFY_CODE_PREFIX = BIZ_PREFIX + "auth:email:verify:code:";
}
