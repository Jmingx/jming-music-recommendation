create table if not exists basic_album
(
    album_id          int auto_increment comment '主键Id'
        primary key,
    album_name        varchar(255)  null comment '专辑名',
    singer_id         int           null comment '歌手Id',
    album_description TEXT          null comment '专辑介绍',
    update_time       datetime on update CURRENT_TIMESTAMP comment '更新时间',
    img_address       varchar(255) comment '封面地址',
    click_number      int default 0 null comment '点击次数',
    show_status       int default 1 null comment '是否展示，逻辑删除'
)
    comment '专辑表';

create table if not exists basic_music
(
    music_id      int auto_increment comment '主键Id'
        primary key,
    music_name    varchar(255)  null comment '歌曲名',
    singer_id     int           null comment '歌手Id',
    album_id      int           null comment '专辑id',
    update_time   datetime on update CURRENT_TIMESTAMP comment '更新时间',
    music_address varchar(255) comment '音乐文件地址',
    img_address   varchar(255) comment '封面地址',
    click_number  int default 0 null comment '点击次数',
    music_type    varchar(40)   null comment '音乐类型',
    music_lyric   text comment '歌词',
    show_status   int default 1 null comment '是否展示，逻辑删除'
)
    comment '音乐表';

create table if not exists basic_singer
(
    singer_id          int auto_increment comment '主键Id'
        primary key,
    singer_name        varchar(255)  null comment '歌手名',
    singer_description TEXT          null comment '歌手介绍',
    update_time        datetime on update CURRENT_TIMESTAMP comment '更新时间',
    img_address        varchar(255) comment '歌手封面地址',
    gender             int default 1 null comment '性别，0表示女，1表示男',
    click_number       int default 0 null comment '点击次数',
    show_status        int default 1 null comment '是否展示，逻辑删除'
)
    comment '歌手表';

create table if not exists basic_consumer
(
    consumer_id           int unsigned auto_increment primary key comment '用户Id',
    consumer_username     varchar(255) not null comment '用户账号',
    consumer_password     varchar(100) not null comment '用户密码',
    consumer_sex          tinyint      null comment '性别',
    consumer_phone_num    char(15)     null comment '电话号码',
    consumer_email        char(30)     null comment '邮件',
    consumer_birth        datetime     null comment '生日',
    consumer_introduction varchar(255) null comment '简介',
    consumer_location     varchar(45)  null comment '地址',
    consumer_avator       varchar(255) null comment '头像地址',
    consumer_create_time  datetime     not null comment '创建时间',
    update_time           datetime     not null comment '更新时间',
    show_status           int default 1 comment '是否展示，逻辑删除',
    constraint email_UNIQUE
        unique (consumer_email),
    constraint phone_num_UNIQUE
        unique (consumer_phone_num),
    constraint username_UNIQUE
        unique (consumer_username)
) comment '用户表';

create table if not exists basic_music_list
(
    music_list_id          int unsigned auto_increment primary key comment '歌单Id',
    music_list_title       varchar(255)            not null comment '歌单标题',
    music_list_img_address varchar(255)            null comment '歌单封面地址',
    music_list_description text                    null comment '歌单描述',
    music_list_style       varchar(10) default '无' null comment '歌单风格',
    update_time            datetime                not null comment '更新时间'
) comment '歌单表';

create table if not exists basic_list_music
(
    list_music_id int unsigned auto_increment primary key comment 'Id',
    music_id      int unsigned not null comment '歌曲Id',
    music_list_id int unsigned not null comment '歌单Id'
) comment '歌单-歌曲中间表';

create table if not exists basic_collect
(
    collect_id    int unsigned auto_increment primary key comment '收藏Id',
    user_id       int unsigned not null comment '用户Id',
    type          tinyint      not null comment '类型',
    music_id      int unsigned null comment '歌曲Id',
    music_list_id int unsigned null comment '歌单Id',
    update_time   datetime     not null comment '更新时间'
) comment '收藏表';

create table if not exists basic_comment
(
    comment_id      int unsigned auto_increment primary key comment '评论Id',
    user_id         int unsigned           not null comment '用户Id',
    music_id        int unsigned           null comment '歌曲Id',
    music_list_id   int unsigned           null comment '歌单Id',
    comment_content varchar(255)           null comment '内容',
    update_time     datetime               not null comment '更新时间',
    comment_type    tinyint                not null comment '类型',
    comment_up      int unsigned default 0 not null comment '点赞',
    unique comment_user (comment_id,user_id)
) comment '评论表';

create table if not exists basic_rank_music
(
    rank_music_id    bigint unsigned auto_increment comment '打分表Id'
        primary key,
    music_id   bigint unsigned        not null comment '歌单Id',
    consumer_id     bigint unsigned        not null comment '用户Id',
    rank_music_score int unsigned default 0 not null comment '分值',
    constraint consumer_music
        unique (consumer_id, music_id)
)
    comment '歌曲打分表';



create table if not exists basic_rank_list
(
    rank_list_id    bigint unsigned auto_increment primary key comment '打分表Id',
    music_list_id   bigint unsigned        not null comment '歌单Id',
    consumer_id     bigint unsigned        not null comment '用户Id',
    rank_list_score int unsigned default 0 not null comment '分值',
    constraint consumer_id
        unique (consumer_id, music_list_id)
) comment '打分表';


# create table if not exists basic_list
# (
#     list_id          int auto_increment comment '主键Id'
#         primary key,
#     list_name        varchar(255)  null comment '歌单名',
#     user_id          int           null comment '创建用户Id',
#     list_description TEXT          null comment '歌单介绍',
#     update_time      datetime on update CURRENT_TIMESTAMP comment '更新时间',
#     img_address      varchar(255) comment '歌单封面地址',
#     list_type        varchar(255)  null comment '歌单类型',
#     click_number     int default 0 null comment '点击次数',
#     show_status      int default 1 null comment '是否展示，逻辑删除'
# )
#     comment '歌单表';

# create table if not exists basic_list_music_relation
# (
#     list_music_relation_id int auto_increment comment '主键Id'
#         primary key,
#     list_id               varchar(255)  null comment '歌单Id',
#     music_id               int           null comment '歌曲Id',
#     update_time           datetime on update CURRENT_TIMESTAMP comment '更新时间',
#     show_status           int default 1 null comment '是否展示，逻辑删除'
# )
#     comment '歌单、歌曲中间表';

# create table if not exists basic_user
# (
#     user_id      int auto_increment comment '主键Id'
#         primary key,
#     user_name    varchar(255) unique null comment '用户名',
#     account      varchar(255)        null comment '用户账号',
#     password     varchar(255)        null comment '用户密码',
#     user_phone   varchar(255)        null comment '电话',
#     user_area    varchar(255)        null comment '地区',
#     user_details varchar(1024)       null comment '简介',
#     user_gender  int                 null comment '性别，0代表女，1代表男',
#     user_birth   date                null comment '生日',
#     user_photo   varchar(255)        null comment '用户头像',
#     update_time  datetime on update CURRENT_TIMESTAMP comment '更新时间',
#     salt         varchar(255)        null comment '动态盐',
#     show_status  int default 1       null comment '是否展示，逻辑删除'
# )
#     comment '用户表';

# create table if not exists basic_user_list_relation
# (
#     user_list_relation_id int auto_increment comment '主键Id'
#         primary key,
#     user_list_id          int           null comment '用户Id',
#     user_id               int           null comment '歌曲Id',
#     update_time           datetime on update CURRENT_TIMESTAMP comment '更新时间',
#     show_status           int default 1 null comment '是否展示，逻辑删除'
# )
#     comment '用户歌单中间表(收藏歌单表)';

# create table if not exists basic_user_music_relation
# (
#     user_music_relation_id int auto_increment comment '主键Id'
#         primary key,
#     user_id               int           null comment '用户Id',
#     music_id               int           null comment '歌曲Id',
#     update_time           datetime on update CURRENT_TIMESTAMP comment '更新时间',
#     show_status           int default 1 null comment '是否展示，逻辑删除'
# )
#     comment '用户歌曲中间表(收藏表)';