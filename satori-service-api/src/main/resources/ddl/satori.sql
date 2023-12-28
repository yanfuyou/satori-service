CREATE DATABASE satori CHARACTER SET utf8mb4 collate utf8mb4_general_ci;

-- auto-generated definition
create table comment
(
    id             bigint auto_increment
        primary key,
    content_id     bigint                               not null comment '内容id',
    create_user_id bigint                               not null comment '评论用户id',
    parent_id      bigint                               not null comment '父级id，根为0',
    detail         varchar(1024)                        not null comment '详情',
    deleted        tinyint(1) default 0                 null,
    create_time    datetime   default CURRENT_TIMESTAMP null,
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '评论信息表';

create index idx_content_id
    on comment (content_id);

-- auto-generated definition
create table content
(
    id             bigint auto_increment
        primary key,
    title          varchar(64)                          null comment '标题',
    category_id    bigint                               null comment '类别id',
    detail         text                                 not null comment '详情',
    create_user_id bigint                               not null comment '创建者id',
    deleted        tinyint(1) default 0                 null,
    create_time    datetime   default CURRENT_TIMESTAMP null,
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create index idx_cate_id
    on content (category_id)
    comment '类别id索引';

create index idx_user_id
    on content (create_user_id)
    comment '用户id索引';

-- auto-generated definition
create table content_category
(
    id             bigint auto_increment
        primary key,
    name           varchar(32)                          not null comment '类别名称',
    create_user_id bigint                               not null comment '创建者id',
    deleted        tinyint(1) default 0                 null comment '删除标记',
    create_time    datetime   default CURRENT_TIMESTAMP null,
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

create index idx_user_id
    on content_category (create_user_id);

-- auto-generated definition
create table custom_system_config
(
    id          bigint auto_increment
        primary key,
    param_name  varchar(20) not null comment '参数名',
    param_value varchar(64) not null comment '参数值',
    type        varchar(20) null comment '类别',
    constraint unique_param_name
        unique (param_name) comment '参数名不允许重复'
);

-- auto-generated definition
create table pet_deeds
(
    id          bigint auto_increment
        primary key,
    pet_id      bigint                               not null comment '宠物id',
    title       varchar(50)                          null comment '事件标题',
    occ_time    datetime                             not null comment '发生时间',
    pics        varchar(1024)                        not null comment '图片,多张[,]分割',
    description varchar(500)                         not null comment '事件描述',
    read_count  int        default 0                 not null comment '查看次数',
    state       tinyint(1) default 0                 null comment '状态 0-初始化,1-已解决',
    deleted     tinyint(1) default 0                 null comment '是否删除',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '宠物';

-- auto-generated definition
create table pet_info
(
    id          bigint auto_increment
        primary key,
    owner_id    bigint                               null comment '主人id',
    alias       varchar(15)                          not null comment '宠物别名',
    description varchar(50)                          null comment '宠物描述',
    avatar      varchar(128)                         not null comment '宠物头像',
    age         int(3)                               null comment '宠物年龄',
    birthday    datetime                             null comment '宠物生日',
    state       tinyint(1) default 1                 null comment '状态',
    deleted     tinyint(1) default 0                 null comment '是否删除',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '宠物';

-- auto-generated definition
create table sys_file_info
(
    id             bigint auto_increment
        primary key,
    source_name    varchar(128)                          null comment '原始名称',
    req_url        varchar(256)                          not null comment '访问路径',
    type           varchar(20) default ''                null comment '文件类型',
    create_user_id bigint                                null comment '上传者id',
    privated       tinyint(1)  default 0                 not null comment '是否私有',
    local_path     varchar(128)                          not null comment '本地路径',
    create_time    datetime    default CURRENT_TIMESTAMP null,
    update_time    datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '文件信息';

create index idx_create_user_id
    on sys_file_info (create_user_id);

create index idx_source_name
    on sys_file_info (source_name(20));

-- auto-generated definition
create table user
(
    id               bigint auto_increment comment '自增主键'
        primary key,
    user_name        varchar(10)                        not null comment '用户名',
    nike_name        varchar(20)                        null comment '昵称',
    user_password    varchar(64)                        not null comment '密码密文',
    user_salt        varchar(8)                         not null comment '盐值',
    user_avatar      varchar(128)                       null comment '头像',
    disable          tinyint  default 0                 null comment '禁用',
    disable_end_time datetime                           null comment '禁用结束时间',
    deleted          tinyint  default 0                 null comment '删除标记',
    create_time      datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time      datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    user_email       varchar(64)                        null comment '用户邮箱',
    constraint idx_user_name
        unique (user_name),
    constraint uni_idx_user_name
        unique (user_name)
);

create index idx_nike_name
    on user (nike_name(5));

-- auto-generated definition
create table user_ext
(
    id           bigint auto_increment
        primary key,
    user_id      bigint                             not null comment '用户id',
    introduction varchar(128)                       null comment '自我据介绍',
    one_words    varchar(128)                       null comment '今日一言',
    phone_number varchar(11)                        null comment '手机号',
    create_time  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户扩展信息';

create index idx_user_id
    on user_ext (user_id);

-- auto-generated definition
create table user_friend_rel
(
    user_id        bigint                               not null comment '用户id',
    user_friend_id bigint                               not null comment '好友id',
    remark         varchar(20)                          null comment '好友备注',
    deleted        tinyint(1) default 0                 not null,
    create_time    datetime   default CURRENT_TIMESTAMP null,
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    status         tinyint(1) default 0                 null comment '当前状态'
)
    comment '好友列表';

create index idx_user_id
    on user_friend_rel (user_id);

-- auto-generated definition
create table user_group
(
    id             bigint auto_increment
        primary key,
    create_user_id bigint                               not null comment '创建者id',
    group_name     varchar(30)                          not null comment '用户组名',
    process        tinyint(1) default 0                 null comment '是否需要审核',
    type           tinyint(1) default 1                 null comment '群聊类型',
    description    varchar(32)                          null comment '简介',
    disable        tinyint(1) default 0                 null,
    deleted        tinyint(1) default 0                 null,
    create_time    datetime   default CURRENT_TIMESTAMP null,
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint uq_group_name
        unique (group_name)
)
    comment '用户组';

-- auto-generated definition
create table user_group_rel
(
    user_id     bigint                               not null comment '用户id',
    group_id    bigint                               not null comment '群聊id',
    status      tinyint(1) default 0                 null comment '当前状态',
    deleted     tinyint(1) default 0                 null,
    create_time datetime   default CURRENT_TIMESTAMP null,
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
)
    comment '用户组关系';

-- auto-generated definition
create table user_message
(
    id                bigint auto_increment
        primary key,
    parent_message_id bigint   default 0                 null comment '父消息id',
    sender_id         bigint                             not null comment '发送者id',
    receiver_id       bigint                             not null comment '接收者id,群或用户',
    receiver_type     tinyint                            not null comment '接收者类型,1-用户,2-群',
    deleted           tinyint  default 0                 not null,
    create_time       datetime default CURRENT_TIMESTAMP null,
    update_time       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    message_content   varchar(1024)                      not null comment '消息内容'
);

create index idx_receiver_id
    on user_message (receiver_id);

create index idx_sender_user
    on user_message (sender_id);

-- auto-generated definition
create table user_todo
(
    id             bigint auto_increment
        primary key,
    create_user_id bigint                               not null comment '创建者',
    content        varchar(20)                          not null comment '代办内容',
    state          tinyint(1) default 0                 null comment '状态 0-初始化,1-已解决',
    deleted        tinyint(1) default 0                 null comment '是否删除',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户代办列表';

create index idx_create_user_id
    on user_todo (create_user_id);

