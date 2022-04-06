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

create table if not exists basic_list
(
    list_id          int auto_increment comment '主键Id'
        primary key,
    list_name        varchar(255)  null comment '歌单名',
    user_id          int           null comment '创建用户Id',
    list_description TEXT          null comment '歌单介绍',
    update_time      datetime on update CURRENT_TIMESTAMP comment '更新时间',
    img_address      varchar(255) comment '歌单封面地址',
    list_type        varchar(255)  null comment '歌单类型',
    click_number     int default 0 null comment '点击次数',
    show_status      int default 1 null comment '是否展示，逻辑删除'
)
    comment '歌单表';

create table if not exists basic_list_song_relation
(
    list_song_relation_id int auto_increment comment '主键Id'
        primary key,
    list_id               varchar(255)  null comment '歌单Id',
    song_id               int           null comment '歌曲Id',
    update_time           datetime on update CURRENT_TIMESTAMP comment '更新时间',
    show_status           int default 1 null comment '是否展示，逻辑删除'
)
    comment '歌单、歌曲中间表';

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

create table if not exists basic_user
(
    user_id      int auto_increment comment '主键Id'
        primary key,
    user_name    varchar(255) unique null comment '用户名',
    account      varchar(255)        null comment '用户账号',
    password     varchar(255)        null comment '用户密码',
    user_phone   varchar(255)        null comment '电话',
    user_area    varchar(255)        null comment '地区',
    user_details varchar(1024)       null comment '简介',
    user_gender  int                 null comment '性别，0代表女，1代表男',
    user_birth   date                null comment '生日',
    user_photo   varchar(255)        null comment '用户头像',
    update_time  datetime on update CURRENT_TIMESTAMP comment '更新时间',
    salt         varchar(255)        null comment '动态盐',
    show_status  int default 1       null comment '是否展示，逻辑删除'
)
    comment '用户表';

create table if not exists basic_user_list_relation
(
    user_list_relation_id int auto_increment comment '主键Id'
        primary key,
    user_list_id          int           null comment '用户Id',
    user_id               int           null comment '歌曲Id',
    update_time           datetime on update CURRENT_TIMESTAMP comment '更新时间',
    show_status           int default 1 null comment '是否展示，逻辑删除'
)
    comment '用户歌单中间表(收藏歌单表)';

create table if not exists basic_user_song_relation
(
    user_song_relation_id int auto_increment comment '主键Id'
        primary key,
    user_id               int           null comment '用户Id',
    song_id               int           null comment '歌曲Id',
    update_time           datetime on update CURRENT_TIMESTAMP comment '更新时间',
    show_status           int default 1 null comment '是否展示，逻辑删除'
)
    comment '用户歌曲中间表(收藏表)';