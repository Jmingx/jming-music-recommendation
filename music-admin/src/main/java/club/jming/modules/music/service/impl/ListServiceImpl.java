package club.jming.modules.music.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.ListDao;
import club.jming.modules.music.entity.ListEntity;
import club.jming.modules.music.service.ListService;


@Service("listService")
public class ListServiceImpl extends ServiceImpl<ListDao, ListEntity> implements ListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ListEntity> page = this.page(
                new Query<ListEntity>().getPage(params),
                new QueryWrapper<ListEntity>()
        );

        return new PageUtils(page);
    }

}