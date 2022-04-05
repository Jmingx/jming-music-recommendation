package club.jming.musicSpider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.musicSpider.domain.BasicListSongRelation;
import club.jming.musicSpider.service.BasicListSongRelationService;
import club.jming.musicSpider.mapper.BasicListSongRelationMapper;
import org.springframework.stereotype.Service;

/**
* @author 78289
* @description 针对表【basic_list_song_relation(歌单、歌曲中间表)】的数据库操作Service实现
* @createDate 2022-04-04 14:25:53
*/
@Service
public class BasicListSongRelationServiceImpl extends ServiceImpl<BasicListSongRelationMapper, BasicListSongRelation>
    implements BasicListSongRelationService{

}




