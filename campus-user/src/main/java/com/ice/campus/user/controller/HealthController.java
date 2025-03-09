package com.ice.campus.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/3/9 18:14
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "alive...";
    }
}
