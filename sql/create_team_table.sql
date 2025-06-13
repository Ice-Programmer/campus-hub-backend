-- 创建库
create database if not exists campus_team;

-- 切换库
use campus_team;

-- 队伍表
create table if not exists team
(
    `id`              bigint auto_increment comment 'id' primary key,
    `team_name`       varchar(128)                       not null comment '队伍名称',
    `description`     text                               null comment '队伍描述',
    `creator_id`       bigint                             not null comment '队长用户id',
    `max_members`     int      default 10                not null comment '最大成员数',
    `current_members` int      default 1                 not null comment '当前成员数',
    `team_type`       tinyint  default 1                 not null comment '队伍类型：1-学习小组，2-项目团队，3-竞赛队伍，4-社团组织，5-兴趣小组',
    `status`          tinyint  default 0                 not null comment '状态：0-审批中，1-招募中，2-活动中，3-停止招募，4-封禁中，5-审批失败',
    `is_public`       tinyint  default 1                 not null comment '是否公开：0-私有，1-公开',
    `is_apply`        tinyint  default 1                 not null comment '加入是否需要审批（0-不需要，1-需要）',
    `tags`            varchar(1024)                      null comment '标签（JSON格式）',
    `create_time`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`       tinyint  default 0                 not null comment '是否删除',
    index idx_creator_id (`creator_id`),
    index idx_team_type (`team_type`),
    index idx_status (`status`)
) comment '队伍表' collate = utf8mb4_unicode_ci;

-- 队伍成员表
create table if not exists team_member
(
    `id`          bigint auto_increment comment 'id' primary key,
    `team_id`     bigint                             not null comment '队伍id',
    `user_id`     bigint                             not null comment '用户id',
    `role_id`     bigint   default 3                 not null comment '角色 id(1-创建者 2-管理员 3-队员)',
    `join_time`   datetime default CURRENT_TIMESTAMP not null comment '加入时间',
    `status`      tinyint  default 1                 not null comment '状态：0-已退出，1-正常',
    `create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    unique key uk_team_user (`team_id`, `user_id`),
    index idx_team_id (`team_id`),
    index idx_user_id (`user_id`)
) comment '队伍成员表' collate = utf8mb4_unicode_ci;

-- 队伍成员角色表
create table if not exists member_role
(
    `id`             bigint auto_increment comment 'id' primary key,
    `create_user_id` bigint                             not null comment '创建用户 id',
    `team_id`        bigint                             null comment '队伍 id（可以关联队伍）',
    `description`    varchar(1024)                      null comment '介绍',
    `is_official`    tinyint  default 0                 not null comment '是否官方(0-不是，1-是)',
    `role_name`      varchar(128)                       not null comment '角色名称',
    `create_time`    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `is_delete`      tinyint  default 0                 not null comment '是否删除',
    index idx_team_id (`team_id`),
    index idx_is_official (`is_official`)
) comment '队伍成员角色表' collate = utf8mb4_unicode_ci;

-- 队伍申请表
create table if not exists team_application
(
    `id`             bigint auto_increment comment 'id' primary key,
    `team_id`        bigint                             not null comment '队伍id',
    `user_id`        bigint                             not null comment '申请用户id',
    `message`        varchar(512)                       null comment '申请留言',
    `status`         tinyint  default 0                 not null comment '状态：0-待审核，1-已通过，2-已拒绝',
    `processed_by`   bigint                             null comment '处理人id',
    `processed_time` datetime                           null comment '处理时间',
    `create_time`    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_team_id (`team_id`),
    index idx_user_id (`user_id`)
) comment '队伍申请表' collate = utf8mb4_unicode_ci;