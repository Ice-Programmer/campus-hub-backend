package com.ice.campus.user.mapper;

import com.ice.campus.common.auth.vo.UserBasicInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/9 15:11
 */
@Slf4j
@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void getUserBasicInfoByEmail() {
        String testEmail = "13706531210@163.com";
        UserBasicInfo info = userMapper.getUserBasicInfoByEmail(testEmail);
        // 打印结果（用于调试）
        log.info("查询结果：{}", info);
        // 断言不为空（根据你测试库里的数据情况修改）
        assertNotNull(info, "应能通过邮箱查到用户信息");
        assertEquals(testEmail, info.getEmail(), "邮箱应一致");
    }
}