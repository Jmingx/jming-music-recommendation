package club.jming.musicServer.dao;

import club.jming.musicServer.domain.RankList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankListMapper extends BaseMapper<RankList> {

//    int insert(RankList record);
//
//    int insertSelective(RankList record);
//
    /**
     * 查总分
     * @param musicListId
     * @return
     */
    int selectScoreSum(@Param("musicListId") Long musicListId);
//
    /**
     * 查总评分人数
     * @param musicListId
     * @return
     */
    int selectRankNum(@Param("musicListId") Long musicListId);
//
//    /**
//     * 查制定用户评分
//     * @param consumerId
//     * @param MusicListId
//     * @return
//     */
//    int selectUserRank(@Param("consumerId") Long consumerId, @Param("MusicListId")  Long MusicListId);
}
