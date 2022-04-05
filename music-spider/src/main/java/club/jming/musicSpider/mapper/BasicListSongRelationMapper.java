package club.jming.musicSpider.mapper;

import club.jming.musicSpider.domain.BasicListSongRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 78289
* @description 针对表【basic_list_song_relation(歌单、歌曲中间表)】的数据库操作Mapper
* @createDate 2022-04-04 14:25:53
* @Entity generator.domain.BasicListSongRelation
*/
@Mapper
public interface BasicListSongRelationMapper extends BaseMapper<BasicListSongRelation> {

}




