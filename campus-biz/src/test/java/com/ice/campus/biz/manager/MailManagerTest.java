package com.ice.campus.biz.manager;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 21:47
 */
@SpringBootTest
class MailManagerTest {

    @Resource
    private MailManager mailManager;

    @Test
    void sendMail() {
        // 替换为你的测试邮箱
        String to = "xxx@xx.com";
        String subject = "this is a test mail";
        String content = "Hello, this is a test mail, and I am testing my spring mail sending function. \n" +
                "if see this test mail, it works perfect \n" +
                "DONT REPLY! DONT REPLY!! DONT REPLY!!!";

        boolean result = mailManager.sendMail(to, subject, content);
        assertTrue(result, "邮件发送应该成功");
    }
}