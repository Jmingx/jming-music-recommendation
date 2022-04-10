package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.RankListMapper;
import club.jming.musicServer.domain.RankList;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import club.jming.musicServer.service.RankListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class RankListServiceImpl extends ServiceImpl<RankListMapper, RankList>
        implements RankListService {

//    private static final Logger logger = LogManager.getLogger(RankListServiceImpl.class);

    @Autowired
    private RankListMapper rankMapper;

    @Override
    public boolean addRank(RankList rankList) {
//        return rankMapper.insertSelective(rankList) > 0;
        return this.save(rankList);
    }

    @Override
    public int rankOfMusicListId(Long musicListId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        int rankNum = rankMapper.selectRankNum(musicListId);
        return (rankNum <= 0) ? 0 : rankMapper.selectScoreSum(musicListId) / rankNum;
    }

    @Override
    public int getUserRank(Long consumerId, Long musicListId) {
//        return rankMapper.selectUserRank(consumerId, MusicListId);
        return this.getOne(new QueryWrapper<RankList>().eq("consumer_id",consumerId).eq("music_list_id",musicListId)).getRankListScore();
    }
}
