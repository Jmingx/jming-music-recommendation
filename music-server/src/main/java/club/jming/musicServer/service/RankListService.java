package club.jming.musicServer.service;

import club.jming.musicServer.domain.RankList;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RankListService extends IService<RankList> {

    boolean addRank(RankList rankList);

    int rankOfMusicListId(Long MusicListId);

    int getUserRank(Long consumerId, Long MusicListId);

}
