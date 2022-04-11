package club.jming.musicServer.service;

import club.jming.musicServer.domain.RankMusic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 78289
* @description 针对表【basic_rank_music(歌曲打分表)】的数据库操作Service
* @createDate 2022-04-10 19:37:35
*/
public interface RankMusicService extends IService<RankMusic> {

    boolean addRank(RankMusic rankMusic);

    Object rankOfMusicId(long parseLong);

    int getUserRank(long parseLong, long parseLong1);
}
