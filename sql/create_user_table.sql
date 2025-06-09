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
    `create_time`  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`    tinyint      default 0                 not null comment '是否删除',
    unique key uk_email (`email`),
    index idx_username (`username`),
    index idx_email (`email`)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 角色表
create table if not exists role
(
    `id`          bigint auto_increment comment 'id' primary key,
    `role_code`   varchar(50)                        not null comment '角色编码',
    `role_name`   varchar(128)                       not null comment '角色名称',
    `description` varchar(256)                       null comment '角色描述',
    `status`      tinyint                            not null default 1 comment '状态 0-禁用 1-启用',
    `is_system`   tinyint                            not null default 0 comment '是否系统内置角色 0-否 1-是',
    `data_scope`  tinyint  default 5                 not null COMMENT '数据权限范围 1-全部数据 2-自定义数据 3-本学校数据 4-本学校及以下 5-仅本人数据',
    `sort_order`  int      default 0                 not null comment '排序',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`   tinyint  default 0                 not null comment '是否删除',
    unique key `idx_role_code` (`role_code`),
    key `idx_role_name` (`role_name`)
) comment '角色' collate = utf8mb4_unicode_ci;

-- 权限表
create table if not exists permission
(
    `id`              bigint auto_increment comment 'id' primary key,
    `permission_code` varchar(128)                       not null comment '权限编码',
    `permission_name` varchar(128)                       not null comment '权限名称',
    `parent_id`       bigint                             null comment '父权限 id',
    `status`          tinyint                            not null default 1 comment '状态 0-禁用 1-启用',
    `is_system`       int                                not null default 0 comment '是否系统内置权限 0-否 1-是',
    `description`     varchar(256)                       null comment '权限描述',
    `sort_order`      int      default 0                 not null comment '排序',
    `create_time`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`       tinyint  default 0                 not null comment '是否删除',
    unique key `idx_permission_code` (`permission_code`),
    key `idx_parent_id` (`parent_id`)
) comment '权限' collate = utf8mb4_unicode_ci;

-- 用户角色关联表
create table if not exists user_role
(
    `id`          bigint auto_increment comment 'id' primary key,
    `user_id`     bigint                             not null comment '用户 id',
    `role_id`     bigint                             not null comment '角色 id',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    unique key `uk_user_role` (`user_id`, `role_id`),
    index `idx_user_id` (`user_id`),
    index `idx_role_id` (`role_id`)
) comment '用户角色关联' collate = utf8mb4_unicode_ci;

-- 角色权限关联表
create table if not exists role_permission
(
    `id`            bigint auto_increment comment 'id' primary key,
    `role_id`       bigint                             not null comment '角色 id',
    `permission_id` bigint                             not null comment '权限 id',
    `create_time`   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    unique key `uk_role_permission` (`role_id`, `permission_id`),
    index `idx_role_id` (`role_id`),
    index `idx_permission_id` (`permission_id`)
) comment '角色权限关联' collate = utf8mb4_unicode_ci;

-- 标签表
create table if not exists tag
(
    `id`             bigint auto_increment comment 'id' primary key,
    `tag_name`       varchar(126)                       not null comment '标签名称',
    `create_user_id` bigint                             not null comment '创建用户 id',
    `status`         tinyint  default 0                 not null comment '0-待审核 1-已审核 2-已拒绝',
    `is_official`    tinyint  default 0                 not null comment '是否官方标签(0 - 非 1 - 是)',
    `usage_count`    int      default 0                 not null comment '使用次数',
    `create_time`    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`      tinyint  default 0                 not null comment '是否删除',
    unique key uk_tag_name (`tag_name`)
) comment '标签表' collate = utf8mb4_unicode_ci;

-- 用户标签表
create table if not exists user_tag
(
    `id`          bigint auto_increment comment 'id' primary key,
    `user_id`     bigint                             not null comment '用户 id',
    `tag_id`      bigint                             not null comment '标签 id',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    unique key uk_user_tag (`user_id`, `tag_id`),
    index idx_user_id (`user_id`),
    index idx_tag_id (`tag_id`)
) comment '用户标签关联表' collate = utf8mb4_unicode_ci;

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