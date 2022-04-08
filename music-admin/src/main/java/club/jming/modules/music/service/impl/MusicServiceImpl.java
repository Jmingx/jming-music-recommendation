package club.jming.modules.music.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.MusicDao;
import club.jming.modules.music.entity.MusicEntity;
import club.jming.modules.music.service.MusicService;
import org.springframework.util.StringUtils;


@Service("musicService")
public class MusicServiceImpl extends ServiceImpl<MusicDao, MusicEntity> implements MusicService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<MusicEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)){
            wrapper.like("music_name",key).or().eq("music_id",key);
        }
        IPage<MusicEntity> page = this.page(
                new Query<MusicEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}