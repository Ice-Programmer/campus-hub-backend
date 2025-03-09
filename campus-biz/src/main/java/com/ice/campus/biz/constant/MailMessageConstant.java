package com.ice.campus.biz.constant;

import com.ice.campus.biz.model.mail.enums.VerifyModeEnum;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 23:10
 */
public class MailMessageConstant {

    public static final String VERIFY_CODE_SUBJECT = "【您的应用名称】验证码通知";

    public static String verifyCodeMessage(String verifyCode, VerifyModeEnum modeEnum) {
        return String.format("""
                        尊敬的【用户】：
                        您好！您正在【 %s 】，本次操作的验证码为：
                        【 %s 】
                        验证码有效期【有效期：5 分钟】，请尽快完成操作。
                        如非本人操作，请忽略此邮件。
                        感谢您使用【大学生服务平台】！
                        【Ice Man】团队""",
                modeEnum.getValue(), verifyCode);
    }
}
