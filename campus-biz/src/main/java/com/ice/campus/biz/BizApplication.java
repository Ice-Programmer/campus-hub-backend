package com.ice.campus.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 21:17
 */
@SpringBootApplication(scanBasePackages = {"com.ice.campus"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class BizApplication {
    public static void main(String[] args) {
        SpringApplication.run(BizApplication.class, args);
    }
}
