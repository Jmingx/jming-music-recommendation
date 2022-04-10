package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.ListMusicMapper;
import club.jming.musicServer.domain.ListMusic;
import club.jming.musicServer.service.ListMusicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMusicServiceImpl extends ServiceImpl<ListMusicMapper, ListMusic>
        implements ListMusicService {

    @Autowired
    private ListMusicMapper listMusicMapper;

    @Override
    public List<ListMusic> allListMusic() {
//        return listMusicMapper.allListMusic();
        return this.list();
    }

    @Override
    public boolean updateListMusicMsg(ListMusic listMusic) {
//        return listMusicMapper.updateListMusicMsg(listMusic) > 0;
        return listMusicMapper.updateById(listMusic) > 0;
    }

    @Override
    public boolean deleteListMusic(Integer musicId) {
//        return listMusicMapper.deleteListMusic(MusicId) > 0;
        return this.removeById(musicId);
    }

    @Override
    public boolean addListMusic(ListMusic listMusic) {
//        return listMusicMapper.insertSelective(listMusic) > 0;
        return this.save(listMusic);
    }

    @Override
    public List<ListMusic> listMusicOfMusicId(Integer musicListId) {
//        return listMusicMapper.listMusicOfMusicId(musicListId);
        return this.list(new QueryWrapper<ListMusic>().eq("music_list_id",musicListId));
    }

}
