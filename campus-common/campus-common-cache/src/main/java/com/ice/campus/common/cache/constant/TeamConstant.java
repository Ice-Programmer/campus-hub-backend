package com.ice.campus.common.cache.constant;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/13 16:21
 */
public interface TeamConstant {

    /**
     * 队伍相关 key
     */
    String TEAM_PREFIX = "campus_team:";

    /**
     * 用户创建队伍分布式锁
     */
    String TEAM_CREATE_LOCK_PREFIX = TEAM_PREFIX + "lock:create:";

}
