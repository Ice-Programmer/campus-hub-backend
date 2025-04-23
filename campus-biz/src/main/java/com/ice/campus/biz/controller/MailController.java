package com.ice.campus.biz.controller;

import com.ice.campus.biz.constant.MailMessageConstant;
import com.ice.campus.biz.manager.MailManager;
import com.ice.campus.biz.model.mail.enums.VerifyModeEnum;
import com.ice.campus.biz.model.mail.request.VerifyCodeRequest;
import com.ice.campus.common.cache.constant.CacheConstant;
import com.ice.campus.common.cache.manager.RedisManager;
import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.common.core.utils.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.util.RandomUtil;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 22:20
 */
@RestController
@Slf4j
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailManager mailManager;

    @Resource
    private RedisManager redisManager;

    /**
     * 邮箱发送验证码
     *
     * @param verifyCodeRequest 邮箱请求类
     * @return 验证码
     */
    @PostMapping("/verify/code")
    public BaseResponse<String> sendVerifyCode(@RequestBody VerifyCodeRequest verifyCodeRequest) {
        if (verifyCodeRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "发送邮箱为空");
        }
        // 1. 获取邮箱
        String email = verifyCodeRequest.getEmail();
        ThrowUtils.throwIf(!ValidatorUtil.isValidEmail(email), ErrorCode.PARAMS_ERROR, "邮箱格式错误");

        // 2. 判断是否已经发送过验证码
        String emailHash = DigestUtils.md5DigestAsHex(email.getBytes());
        String sendCacheKey = CacheConstant.EMAIL_VERIFY_SEND_PREFIX + emailHash;
        if (redisManager.exists(sendCacheKey)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "验证码已发送，请不要重复操作");
        }
        redisManager.set(sendCacheKey, CacheConstant.TRUE_VALUE, 1, TimeUnit.MINUTES);

        // 3. 验证码保存 redis 中
        // 生成验证码
        String verifyCodeCacheKey = CacheConstant.EMAIL_VERIFY_CODE_PREFIX + emailHash;
        String verifyCode = RandomUtil.randomNumbers(6);
        log.info("成功生成验证码：「{}」，有效期 5 分钟", verifyCode);
        redisManager.set(verifyCodeCacheKey, verifyCode, 5, TimeUnit.MINUTES);

        // 4. 发送邮箱
        mailManager.sendMail(email,
                MailMessageConstant.VERIFY_CODE_SUBJECT,
                MailMessageConstant.verifyCodeMessage(verifyCode, VerifyModeEnum.LOGIN));

        return ResultUtils.success(verifyCode);
    }
}
