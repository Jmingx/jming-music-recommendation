package club.jming.musicSpider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.musicSpider.domain.BasicSinger;
import club.jming.musicSpider.service.BasicSingerService;
import club.jming.musicSpider.mapper.BasicSingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 78289
* @description 针对表【basic_singer(歌手表)】的数据库操作Service实现
* @createDate 2022-04-04 14:25:53
*/
@Service
public class BasicSingerServiceImpl extends ServiceImpl<BasicSingerMapper, BasicSinger>
    implements BasicSingerService{

    @Autowired
    private BasicSingerMapper singerMapper;

    @Override
    public int updateAndGetId(BasicSinger basicSinger) {
        if (StringUtils.isEmpty(basicSinger.getSingerName())){
            return -1;
        }
        BasicSinger singer = singerMapper.selectOne(new QueryWrapper<BasicSinger>().eq("singer_name", basicSinger.getSingerName()));
        if (singer == null){
            singerMapper.insert(basicSinger);
            return singerMapper.selectOne(new QueryWrapper<BasicSinger>().eq("singer_name", basicSinger.getSingerName())).getSingerId();
        }else {
            singerMapper.update(basicSinger,new UpdateWrapper<BasicSinger>().eq("singer_name",basicSinger.getSingerName()));
        }
        return singer.getSingerId();
    }
}




