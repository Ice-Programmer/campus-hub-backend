package com.ice.campus.common.cache.constant;

/**
 * @author <a href="https://github.com/Ice-Programmer">chenjiahan</a>
 * @create 2025/6/7 12:11
 */
public interface UserConstant {

    /**
     * 用户 相关key
     */
    String USER_PREFIX = "campus_user:";

    /**
     * 学生所有学校列表 key
     */
    String STUDENT_SCHOOL_LIST_KEY = USER_PREFIX + "student:schools";

    /**
     * 学生所有专业列表 key
     */
    String STUDENT_MAJOR_LIST_KEY = USER_PREFIX + "student:majors";
}
