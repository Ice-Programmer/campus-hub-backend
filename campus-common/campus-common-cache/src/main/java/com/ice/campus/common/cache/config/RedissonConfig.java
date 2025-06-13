package com.ice.campus.common.cache.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置类
 *
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/11 22:08
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.data.redis.port:6379}")
    private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;

    @Value("${spring.data.redis.database:10}")
    private int database;

//    @Value("${spring.data.redis.timeout:3000}")
//    private int timeout;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        // 单机模式配置
        String redisUrl = String.format("redis://%s:%d", redisHost, redisPort);
        config.useSingleServer()
                .setAddress(redisUrl)
                .setDatabase(database)
                .setTimeout(3000)
                .setConnectionMinimumIdleSize(5)
                .setConnectionPoolSize(10)
                .setIdleConnectionTimeout(10000)
                .setConnectTimeout(3000)
                .setRetryAttempts(3);

        // 如果有密码则设置密码
        if (redisPassword != null && !redisPassword.trim().isEmpty()) {
            config.useSingleServer().setPassword(redisPassword);
        }

        return Redisson.create(config);
    }
}