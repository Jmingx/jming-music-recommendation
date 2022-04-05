package club.jming.musicSpider.mapper;

import club.jming.musicSpider.domain.BasicMusic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 78289
* @description 针对表【basic_music(音乐表)】的数据库操作Mapper
* @createDate 2022-04-04 14:25:53
* @Entity generator.domain.BasicMusic
*/
@Mapper
public interface BasicMusicMapper extends BaseMapper<BasicMusic> {

}




