package club.jming.musicSpider.service.impl;

import club.jming.musicSpider.domain.BasicSinger;
import club.jming.musicSpider.mapper.BasicSingerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.musicSpider.domain.BasicMusic;
import club.jming.musicSpider.service.BasicMusicService;
import club.jming.musicSpider.mapper.BasicMusicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 78289
* @description 针对表【basic_music(音乐表)】的数据库操作Service实现
* @createDate 2022-04-04 14:25:53
*/
@Service
public class BasicMusicServiceImpl extends ServiceImpl<BasicMusicMapper, BasicMusic>
    implements BasicMusicService{


}




