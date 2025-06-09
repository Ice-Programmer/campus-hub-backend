package com.ice.campus.user.controller;

import com.ice.campus.common.auth.annotation.IgnoreAuth;
import com.ice.campus.common.auth.security.SecurityContext;
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
import com.ice.campus.common.auth.vo.UserBasicInfo;
import com.ice.campus.common.auth.vo.TokenVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @IgnoreAuth
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
        UserBasicInfo userBasicInfo = userService.userLoginByMail(email, verifyCode);

        // 保存 redis
        String userRole = userBasicInfo.getRole();
        String device = DeviceUtil.getRequestDevice(request);
        TokenVO tokenVO = tokenClient.createTokenVOAndStore(userBasicInfo, device);
        log.info("User {} ({} role) Login by {} Successfully! userId: {}",
                userBasicInfo.getUsername(), userRole, device, userBasicInfo.getId());

        return ResultUtils.success(tokenVO);
    }

    /**
     * 用户退出登录
     *
     * @param request request
     * @return 退出登录成功
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        UserBasicInfo currentUser = SecurityContext.getCurrentUser();
        String device = DeviceUtil.getRequestDevice(request);
        // 移除缓存中当前设备登录信息
        tokenClient.removeTokenCache(currentUser, device);
        return ResultUtils.success(true);
    }
}
