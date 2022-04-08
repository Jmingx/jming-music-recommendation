package club.jming.modules.music.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.AlbumDao;
import club.jming.modules.music.entity.AlbumEntity;
import club.jming.modules.music.service.AlbumService;
import org.springframework.util.StringUtils;


@Service("albumService")
public class AlbumServiceImpl extends ServiceImpl<AlbumDao, AlbumEntity> implements AlbumService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<AlbumEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)){
            wrapper.like("album_name",key).or().eq("album_id",key);
        }
        IPage<AlbumEntity> page = this.page(
                new Query<AlbumEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<AlbumEntity> listBySingerId(Integer singerId) {
        QueryWrapper<AlbumEntity> wrapper = new QueryWrapper<AlbumEntity>();
        wrapper.eq("singer_id",singerId);
        return this.list(wrapper);
    }

}