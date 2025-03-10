package com.ice.campus.user.controller;

import com.ice.campus.common.core.common.BaseResponse;
import com.ice.campus.common.core.common.ResultUtils;
import com.ice.campus.common.core.constant.ErrorCode;
import com.ice.campus.common.core.exception.BusinessException;
import com.ice.campus.common.core.exception.ThrowUtils;
import com.ice.campus.common.core.utils.DeviceUtil;
import com.ice.campus.common.core.utils.ValidatorUtil;
import com.ice.campus.user.model.request.user.UserMailLoginRequest;
import com.ice.campus.user.service.UserService;
import com.ice.campus.common.auth.client.TokenClient;
import com.ice.campus.common.auth.vo.LoginVO;
import com.ice.campus.common.auth.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户控制器
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 21:05
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private TokenClient tokenClient;


    @PostMapping("/mail")
    public BaseResponse<TokenVO> userLoginByMail(@RequestBody UserMailLoginRequest userMailLoginRequest,
                                                 HttpServletRequest request) {
        if (userMailLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String email = userMailLoginRequest.getEmail();
        String verifyCode = userMailLoginRequest.getVerifyCode();

        // 校验参数
        ThrowUtils.throwIf(!ValidatorUtil.isValidEmail(email), ErrorCode.PARAMS_ERROR, "邮箱格式错误");
        ThrowUtils.throwIf(verifyCode.length() != 6, ErrorCode.PARAMS_ERROR, "验证码长度错误");

        // 登录
        LoginVO loginVO = userService.userLoginByMail(email, verifyCode);

        // 保存 redis
        String userRole = loginVO.getRole();
        String device = DeviceUtil.getRequestDevice(request);
        TokenVO tokenVO = tokenClient.createTokenVOAndStore(loginVO, device);
        log.info("User {} ({} role) Login by {} Successfully! userId: {}",
                loginVO.getUsername(), userRole, device, loginVO.getId());

        return ResultUtils.success(tokenVO);
    }
}
