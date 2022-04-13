create table if not exists cf_rate
(
    rate_id  int auto_increment comment '主键Id' primary key,
    user_id  int    null comment '用户Id',
    music_id int    null comment '歌曲Id',
    cf_rate  double null comment '得分，推荐的参考依据'
) comment 'user-item评分表';

create table if not exists cf_user_similarity
(
    similarity_id int auto_increment comment 'Id' primary key,
    user_id       int null comment '用户Id',
    user_ref_id   int null comment '相关用户Id',
    similarity    double comment '相似度',
    UNIQUE KEY unique_key (user_id,user_ref_id)
) comment '用户相似表，userCF';

create table if not exists cf_music_similarity
(
    similarity_id int auto_increment comment 'Id' primary key,
    music_id       int null comment '歌曲Id',
    music_ref_id   int null comment '相关歌曲Id',
    similarity    double comment '相似度'
) comment '歌曲相似表，itemCF';