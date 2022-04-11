package club.jming.musicServer.service.impl;

import club.jming.musicServer.domain.RankList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.musicServer.domain.RankMusic;
import club.jming.musicServer.service.RankMusicService;
import club.jming.musicServer.dao.RankMusicMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 78289
 * @description 针对表【basic_rank_music(歌曲打分表)】的数据库操作Service实现
 * @createDate 2022-04-10 19:37:35
 */
@Service
@Slf4j
public class RankMusicServiceImpl extends ServiceImpl<RankMusicMapper, RankMusic>
        implements RankMusicService {

    @Autowired
    private RankMusicMapper rankMusicMapper;

    @Override
    public boolean addRank(RankMusic rankMusic) {
        UpdateWrapper<RankMusic> wrapper = new UpdateWrapper<RankMusic>();
        if (this.count(new QueryWrapper<RankMusic>().eq("music_id",rankMusic.getMusicId()).eq("consumer_id",rankMusic.getConsumerId())) > 0){
            return this.update(rankMusic,wrapper);
        }else {
            return this.save(rankMusic);
        }
//        wrapper.eq("music_id",rankMusic.getMusicId()).eq("consumer_id",rankMusic.getConsumerId());
//        return this.update(rankMusic,wrapper);
    }

    @Override
    public Object rankOfMusicId(long musicId) {
        // 评分总人数如果为 0，则返回0；否则返回计算出的结果
        int rankNum = rankMusicMapper.selectRankNum(musicId);
        return (rankNum <= 0) ? 0 : rankMusicMapper.selectScoreSum(musicId) / rankNum;
    }

    @Override
    public int getUserRank(long consumerId, long musicId) {
        return this.getOne(new QueryWrapper<RankMusic>().eq("consumer_id", consumerId).eq("music_list_id", musicId)).getRankMusicScore();
    }
}




