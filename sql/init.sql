-- 创建库
create database if not exists my_db;

-- 切换库
use my_db;

-- 用户表
create table if not exists user
(
    user_id      bigint auto_increment comment 'user_id' primary key,
    username     varchar(256)                           null comment '用户名',
    `password`   varchar(512)                           null comment '密码',
    phone        varchar(128)                           null comment '电话',
    email        varchar(256)                           null comment '邮箱',
    user_avatar  varchar(1024)                          null comment '用户头像',
    user_profile varchar(512)                           null comment '用户简介',
    user_role    varchar(256) default 'USER'            not null comment '用户角色：USER/ADMIN/BAN',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted      tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    post_id     bigint auto_increment comment 'id' primary key,
    title       varchar(512)                       null comment '标题',
    content     text                               null comment '内容',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    thumb_num   int      default 0                 not null comment '点赞数',
    favour_num  int      default 0                 not null comment '收藏数',
    user_id     bigint                             not null comment '创建用户 id',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     tinyint  default 0                 not null comment '是否删除',
    index idx_userId (user_id)
) comment '帖子' collate = utf8mb4_unicode_ci;
