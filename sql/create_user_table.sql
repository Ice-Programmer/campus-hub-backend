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

-- 学生表
create table if not exists student
(
    `id`              bigint auto_increment comment 'id' primary key,
    `user_id`         bigint                             null comment '用户id',
    `student_grade`   int      default 1                 not null comment '学生学历',
    `school_id`       bigint                             not null comment '学校 id',
    `major_id`        bigint                             not null comment '专业 id',
    `enrollment_year` year                               null comment '入学年份',
    `graduation_year` year                               null comment '预计毕业年份',
    `status`          tinyint  default 1                 not null comment '学生状态:1-在读 2-休学 3-毕业 4-退学',
    `create_time`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`       tinyint  default 0                 not null comment '是否删除',
    index idx_user_id (`user_id`),
    index idx_school_id (`school_id`)
) comment '学生' collate = utf8mb4_unicode_ci;

-- 获奖经历
create table if not exists student_awards
(
    `id`          bigint auto_increment comment 'id' primary key,
    `user_id`     bigint                             null comment '用户id',
    `award_name`  varchar(1024)                      not null comment '奖项名称',
    `award_time`  datetime                           not null comment '获奖时间',
    `description` varchar(1024)                      not null comment '简要介绍',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`   tinyint  default 0                 not null comment '是否删除'
) comment '学生经历' collate = utf8mb4_unicode_ci;

-- 专业表
create table if not exists major
(
    `id`          bigint auto_increment comment 'id' primary key,
    `major_name`  varchar(256)                       not null comment '专业名称',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`   tinyint  default 0                 not null comment '是否删除'
) comment '专业' collate = utf8mb4_unicode_ci;

-- 学校表
create table if not exists school
(
    `id`          bigint auto_increment comment 'id' primary key,
    `school_name` varchar(256)                       not null comment '学校名称',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`   tinyint  default 0                 not null comment '是否删除'
) comment '学校' collate = utf8mb4_unicode_ci;


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

-- 聊天室表
create table if not exists chat_room
(
    `id`             bigint auto_increment comment 'id' primary key,
    `room_name`      varchar(255)                       not null comment '聊天室名称',
    `room_type`      tinyint  default 0                 not null comment '聊天室类型',
    `create_user_id` bigint                             not null comment '创建用户 id',
    `create_time`    datetime default current_timestamp not null comment '创建时间',
    `update_time`    datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`      tinyint  default 0                 not null comment '是否删除',
    index idx_create_user_id (`create_user_id`)
) comment '聊天室表' collate = utf8mb4_unicode_ci;

-- 聊天成员表
create table if not exists chat_room_member
(
    `id`          bigint auto_increment comment 'id' primary key,
    `room_id`     bigint                             not null comment '聊天室 id',
    `user_id`     bigint                             not null comment '用户 id',
    `create_time` datetime default current_timestamp not null comment '创建时间（加入时间）',
    `update_time` datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`   tinyint  default 0                 not null comment '是否删除',
    unique (`room_id`, `user_id`)
) comment '聊天室表' collate = utf8mb4_unicode_ci;

-- 消息存储表
create table if not exists chat_message
(
    `id`               bigint auto_increment comment 'id' primary key,
    `room_id`          bigint                                not null comment '聊天室 id',
    `user_id`          bigint                                not null comment '用户 id',
    `content_type`     varchar(50) default 'text'            not null comment '内容类型(text, image, file, emoji)',
    `content`          varchar(1024)                         not null comment '消息内容',
    `reply_message_id` bigint                                null comment '回复消息 id',
    `create_time`      datetime    default current_timestamp not null comment '创建时间（加入时间）',
    `update_time`      datetime    default current_timestamp not null on update current_timestamp comment '更新时间',
    `is_delete`        tinyint     default 0                 not null comment '是否删除',
    index idx_room_id (`room_id`),
    index idx_user_id (`user_id`)
) comment '聊天消息存储表' collate = utf8mb4_unicode_ci;

-- 消息已读表
create table if not exists chat_message_read
(
    `id`         bigint auto_increment comment 'id' primary key,
    `message_id` bigint                             not null comment '消息ID',
    `user_id`    bigint                             not null comment '用户ID',
    `room_id`    bigint                             not null comment '聊天室ID',
    `read_time`  datetime default current_timestamp null comment '已读时间',
    index idx_message_id (`message_id`),
    index idx_user_id (`user_id`),
    index idx_room_id (`room_id`)
) comment ='聊天消息已读记录表' collate = utf8mb4_unicode_ci;

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