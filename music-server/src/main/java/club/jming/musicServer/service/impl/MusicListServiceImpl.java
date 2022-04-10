package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.MusicListMapper;
import club.jming.musicServer.domain.MusicList;
import club.jming.musicServer.service.MusicListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicListServiceImpl extends ServiceImpl<MusicListMapper, MusicList>
        implements MusicListService {

    @Autowired
    private MusicListMapper musicListMapper;

    @Override
    public boolean updateMusicListMsg(MusicList musicList) {
//        return musicListMapper.updateMusicListMsg(MusicList) >0 ?true:false;
        return this.updateById(musicList);
    }

    @Override
    public boolean deleteMusicList(Integer id) {
//        return musicListMapper.deleteMusicList(id) >0 ?true:false;
        return this.removeById(id);
    }

    @Override
    public List<MusicList> allMusicList() {
//        return musicListMapper.allMusicList();
        return this.list();
    }

    @Override
    public List<MusicList> likeTitle(String title) {
//        return musicListMapper.likeTitle(title);
        return this.list(new QueryWrapper<MusicList>().like("music_list_title",title));
    }

    @Override
    public List<MusicList> likeStyle(String style) {
//        return musicListMapper.likeStyle(style);
        return this.list(new QueryWrapper<MusicList>().like("music_list_style",style));
    }

    @Override
    public List<MusicList> musicListOfTitle(String title) {
//        return musicListMapper.MusicListOfTitle(title);
        return this.list(new QueryWrapper<MusicList>().eq("music_list_title",title));
    }

    @Override
    public boolean addMusicList(MusicList musicList) {
//        return musicListMapper.insertSelective(MusicList) > 0 ? true : false;
        return this.save(musicList);
    }

    @Override
    public boolean updateMusicListImg(MusicList musicList) {
//        return musicListMapper.updateMusicListImg(MusicList) > 0 ? true : false;
        return this.updateById(musicList);
    }
}
