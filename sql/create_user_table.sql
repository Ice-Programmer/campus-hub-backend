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
    `user_avatar`  varchar(256)                          null comment '用户头像',
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
