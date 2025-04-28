-- 创建库
create database if not exists campus_user;

-- 切换库
use campus_user;

-- 用户表
create table if not exists user
(
    `id`           bigint auto_increment comment 'id' primary key,
    `username`     varchar(126)                           null comment '用户昵称',
    `email`        varchar(256)                           null comment '邮箱',
    `user_avatar`  varchar(256)                           null comment '用户头像',
    `gender`       tinyint      default 0                 not null comment '0-女 1-男 2',
    `university`   bigint                                 null comment '学校',
    `education`    tinyint                                null comment '学历',
    `user_profile` varchar(512)                           null comment '用户简介',
    `birthday`     date                                   null comment '生日',
    `city`         bigint                                 null comment '城市',
    `followee_num` int unsigned default 0                 not null comment '粉丝数',
    `follow_num`   int unsigned default 0                 not null comment '关注数',
    `role`         varchar(128) default 'user'            not null comment '用户角色：user/admin/ban',
    `create_time`  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`    tinyint      default 0                 not null comment '是否删除',
    unique key uk_email (`email`),
    index idx_username (`username`),
    index idx_email (`email`)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 讲师信息表
create table if not exists teacher
(
    `id`          bigint auto_increment comment 'id' primary key,
    `user_id`     bigint                             not null comment '讲师id',
    `title`       varchar(128)                       null comment '职称',
    `expertise`   varchar(256)                       null comment '专长领域',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `update_time` datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`   tinyint  default 0                 not null comment '是否删除',
    index idx_teacher_id (`user_id`)
) comment '讲师信息表' collate = utf8mb4_unicode_ci;

-- 插入用户数据
INSERT INTO user (username, email, user_avatar, gender, university, education, user_profile, birthday, city, role)
VALUES
    -- 教师用户
    ('陈教授', 'chen.prof@example.com', 'https://example.com/avatar4.jpg', 1, 1, 4, '从事计算机科学教育20年',
     '1975-08-10', 1, 'teacher'),
    ('林博士', 'lin.dr@example.com', 'https://example.com/avatar5.jpg', 0, 2, 4, '人工智能与机器学习专家', '1980-12-25',
     2, 'teacher'),
    ('黄讲师', 'huang.lecturer@example.com', 'https://example.com/avatar6.jpg', 1, 3, 3, '专注Web开发技术教学',
     '1985-06-30', 3, 'teacher');

-- 插入教师信息数据
INSERT INTO teacher (user_id, title, expertise)
VALUES (4, '教授', '计算机体系结构,操作系统,分布式系统'),
       (5, '副教授', '机器学习,深度学习,计算机视觉'),
       (6, '讲师', 'Web前端开发,后端架构,数据库设计');