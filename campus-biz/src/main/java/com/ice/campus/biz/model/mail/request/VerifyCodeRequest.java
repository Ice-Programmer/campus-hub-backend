package com.ice.campus.biz.model.mail.request;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 邮箱验证码请求类
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 22:28
 */
@Data
public class VerifyCodeRequest implements Serializable {

    /**
     * 邮箱
     */
    private String email;

    @Serial
    private static final long serialVersionUID = 7002263956896729551L;
}
