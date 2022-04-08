package club.jming.modules.music.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.ListSongRelationDao;
import club.jming.modules.music.entity.ListSongRelationEntity;
import club.jming.modules.music.service.ListSongRelationService;


@Service("listSongRelationService")
public class ListSongRelationServiceImpl extends ServiceImpl<ListSongRelationDao, ListSongRelationEntity> implements ListSongRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ListSongRelationEntity> page = this.page(
                new Query<ListSongRelationEntity>().getPage(params),
                new QueryWrapper<ListSongRelationEntity>()
        );

        return new PageUtils(page);
    }

}