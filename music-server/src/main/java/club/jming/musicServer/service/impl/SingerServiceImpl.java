package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.SingerMapper;
import club.jming.musicServer.domain.Music;
import club.jming.musicServer.domain.Singer;
import club.jming.musicServer.service.MusicService;
import club.jming.musicServer.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer>
        implements SingerService {

    @Autowired
    private MusicService musicService;

    @Override
    public boolean updateSingerMsg(Singer singer) {
//        return singerMapper.updateSingerMsg(singer) > 0;
        return this.updateById(singer);
    }

    @Override
    public boolean updateSingerPic(Singer singer) {
//        return singerMapper.updateSingerPic(singer) > 0;
        return this.updateById(singer);
    }

    @Override
    public boolean deleteSinger(Integer id) {
//        return singerMapper.deleteSinger(id) > 0;
        return this.removeById(id);
    }

    @Override
    public List<Singer> allSinger() {
//        return singerMapper.allSinger();
        return this.list();
    }

    @Override
    public boolean addSinger(Singer singer) {
//        return singerMapper.insertSelective(singer) > 0;
        return this.save(singer);
    }

    @Override
    public List<Singer> singerOfName(String name) {
//        return singerMapper.singerOfName(name);
        return this.list(new QueryWrapper<Singer>().eq("singer_name",name));
    }

    @Override
    public List<Singer> singerOfSex(Integer sex) {
//        return singerMapper.singerOfSex(sex);
        return this.list(new QueryWrapper<Singer>().eq("gender",sex));
    }

    @Override
    public Integer getIdByName(String name) {
        return this.getOne(new QueryWrapper<Singer>().eq("singer_name", name)).getSingerId();
    }

    public Singer findByMusicId(Long musicId) {
        QueryWrapper<Music> wrapper = new QueryWrapper<Music>();
        wrapper.eq("music_id",musicId);
        Music music = this.musicService.getOne(wrapper);
        return this.getOne(new QueryWrapper<Singer>().eq("singer_id",music.getSingerId()));
    }
}
