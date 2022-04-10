package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.MusicMapper;
import club.jming.musicServer.dao.SingerMapper;
import club.jming.musicServer.domain.Music;
import club.jming.musicServer.domain.Singer;
import club.jming.musicServer.service.MusicService;
import club.jming.musicServer.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
        implements MusicService {

    @Autowired
    private SingerService singerService;

    @Override
    public List<Music> allMusic() {
//        return musicMapper.allMusic();
        return this.list();
    }

    @Override
    public boolean addMusic(Music music) {
//        return musicMapper.insertSelective(Music) > 0 ? true : false;
        return this.save(music);
    }

    @Override
    public boolean updateMusicMsg(Music music) {
//        return musicMapper.updateMusicMsg(music) > 0 ? true : false;
        return this.updateById(music);
    }

    @Override
    public boolean updateMusicUrl(Music music) {
//        return musicMapper.updateMusicUrl(Music) > 0 ? true : false;
        return this.updateById(music);
    }

    @Override
    public boolean updateMusicPic(Music music) {
//        return musicMapper.updateMusicPic(music) > 0 ? true : false;
        return this.updateById(music);
    }

    @Override
    public boolean deleteMusic(Integer id) {
//        return musicMapper.deleteMusic(id) > 0 ? true : false;
        return this.removeById(id);
    }

    @Override
    public List<Music> musicOfSingerId(Integer singerId) {
//        return musicMapper.musicOfSingerId(singerId);
        return this.list(new QueryWrapper<Music>().eq("singer_id",singerId));
    }

    @Override
    public List<Music> musicOfId(Integer id) {
//        return musicMapper.musicOfId(id);
        return this.list(new QueryWrapper<Music>().eq("music_id",id));
    }

    @Override
    public List<Music> musicOfSingerName(String name) {
        List<Music> res = new LinkedList<>();
        res.addAll(this.list(new QueryWrapper<Music>().like("music_name",name)));
        List<Singer> singers = this.singerService.list(new QueryWrapper<Singer>().like("singer_name", name));
        for (Singer singer : singers) {
            res.addAll(this.list(new QueryWrapper<Music>().eq("singer_id",singer.getSingerId())));
        }
        return res;
    }

    @Override
    public List<Music> musicOfName(String name) {
//        return musicMapper.musicOfName(name);
        return this.list(new QueryWrapper<Music>().eq("music_name",name));
    }
}
