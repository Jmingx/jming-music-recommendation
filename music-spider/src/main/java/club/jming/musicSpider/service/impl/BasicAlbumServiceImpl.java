package club.jming.musicSpider.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.musicSpider.domain.BasicAlbum;
import club.jming.musicSpider.service.BasicAlbumService;
import club.jming.musicSpider.mapper.BasicAlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author 78289
* @description 针对表【basic_album(专辑表)】的数据库操作Service实现
* @createDate 2022-04-04 14:25:53
*/
@Service
public class BasicAlbumServiceImpl extends ServiceImpl<BasicAlbumMapper, BasicAlbum>
    implements BasicAlbumService{

    @Autowired
    private BasicAlbumMapper albumMapper;

    @Override
    public int updateAndGetId(BasicAlbum album) {
        if (StringUtils.isEmpty(album)){
            return -1;
        }
        BasicAlbum basicAlbum = albumMapper.selectOne(new QueryWrapper<BasicAlbum>().eq("album_name", album.getAlbumName()));
        if (basicAlbum == null){
            albumMapper.insert(album);
            return albumMapper.selectOne(new QueryWrapper<BasicAlbum>().eq("album_name", album.getAlbumName())).getAlbumId();
        }else {
            UpdateWrapper<BasicAlbum> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("album_name",album.getAlbumName());
            albumMapper.update(album, updateWrapper);
            return albumMapper.selectOne(new QueryWrapper<BasicAlbum>().eq("album_name", album.getAlbumName())).getAlbumId();
        }
    }
}




