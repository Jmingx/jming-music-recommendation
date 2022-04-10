package club.jming.musicServer.service.impl;

import club.jming.musicServer.dao.CollectMapper;
import club.jming.musicServer.domain.Collect;
import club.jming.musicServer.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
        implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public boolean addCollection(Collect collect) {
//        return collectMapper.insertSelective(collect) > 0 ? true : false;
        return collectMapper.insert(collect) > 0;
    }

    @Override
    public boolean existMusicId(Integer userId, Integer musicId) {
//        return collectMapper.existMusicId(userId, MusicId) > 0 ? true : false;
        QueryWrapper<Collect> wrapper = new QueryWrapper<Collect>();
        wrapper.eq("user_id", userId).eq("music_id", musicId);
        return this.count(wrapper) > 0;
    }

    @Override
    public boolean updateCollectMsg(Collect collect) {
        return collectMapper.updateById(collect) > 0 ? true : false;
    }

    @Override
    public boolean deleteCollect(Integer userId, Integer musicId) {
//        return collectMapper.deleteCollect(userId, MusicId) > 0 ? true : false;
        QueryWrapper<Collect> wrapper = new QueryWrapper<Collect>();
        wrapper.eq("user_id", userId).eq("music_id", musicId);
        return collectMapper.delete(wrapper) > 0;
    }

    @Override
    public List<Collect> allCollect() {
        return this.list();
    }

    @Override
    public List<Collect> collectionOfUser(Integer userId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
//        return collectMapper.collectionOfUser(userId);
        return this.list(wrapper);
    }
}
