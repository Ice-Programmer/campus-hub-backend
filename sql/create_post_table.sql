-- 创建库
create database if not exists campus_post;

-- 切换库
use campus_post;

create table if not exists post
(
    id          bigint auto_increment primary key  not null comment 'id',
    user_id     bigint                             not null comment '创建用户',
    title       varchar(512)                       not null comment '标题',
    content     text                               null comment '内容',
    thumb_num   int      default 0                 not null comment '点赞数',
    favour_num  int      default 0                 not null comment '收藏数',
    comment_num int      default 0                 not null comment '评论数',
    address     varchar(128)                       null comment '发布地点(文本地址)',
    city_code   varchar(20)                        null comment '城市行政区划代码',
    location    geometry                           not null comment '空间位置',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete   tinyint  default 0                 not null comment '是否删除',
    index idx_user_id (user_id),
    index idx_create_time (create_time),
    index idx_city_code (city_code),
    spatial index idx_spatial (location)
) comment '帖子表' collate = utf8mb4_unicode_ci;