package club.jming.musicServer.dao;

import club.jming.musicServer.domain.RankList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RankListMapper {

    int insert(RankList record);

    int insertSelective(RankList record);

    /**
     * 查总分
     * @param songListId
     * @return
     */
    int selectScoreSum(@Param("songListId") Long songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    int selectRankNum(@Param("songListId") Long songListId);

    /**
     * 查制定用户评分
     * @param consumerId
     * @param songListId
     * @return
     */
    int selectUserRank(@Param("consumerId") Long consumerId, @Param("songListId")  Long songListId);
}
