package club.jming.musicServer.dao;

import club.jming.musicServer.domain.RankMusic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author 78289
* @description 针对表【basic_rank_music(歌曲打分表)】的数据库操作Mapper
* @createDate 2022-04-10 19:37:35
* @Entity generator.domain.RankMusic
*/
@Repository
public interface RankMusicMapper extends BaseMapper<RankMusic> {

    /**
     * 查总分
     * @param musicId
     * @return
     */
    int selectScoreSum(@Param("musicId") Long musicId);
//
    /**
     * 查总评分人数
     * @param musicId
     * @return
     */
    int selectRankNum(@Param("musicId") Long musicId);

}




