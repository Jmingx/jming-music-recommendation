package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.SingerMapper;
import club.jming.musicServer.domain.Singer;
import club.jming.musicServer.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateSingerMsg(singer) > 0;
    }

    @Override
    public boolean updateSingerPic(Singer singer) {

        return singerMapper.updateSingerPic(singer) > 0;
    }

    @Override
    public boolean deleteSinger(Integer id) {
        return singerMapper.deleteSinger(id) > 0;
    }

    @Override
    public List<Singer> allSinger()
    {
        return singerMapper.allSinger();
    }

    @Override
    public boolean addSinger(Singer singer)  {

        return singerMapper.insertSelective(singer) > 0;
    }

    @Override
    public List<Singer> singerOfName(String name)

    {
        return singerMapper.singerOfName(name);
    }

    @Override
    public List<Singer> singerOfSex(Integer sex)

    {
        return singerMapper.singerOfSex(sex);
    }
}
