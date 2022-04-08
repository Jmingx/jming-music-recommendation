package club.jming.modules.music.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.SingerDao;
import club.jming.modules.music.entity.SingerEntity;
import club.jming.modules.music.service.SingerService;
import org.springframework.util.StringUtils;


@Service("singerService")
public class SingerServiceImpl extends ServiceImpl<SingerDao, SingerEntity> implements SingerService {

    @Autowired
    private SingerDao singerDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<SingerEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)){
            wrapper.like("singer_name",key).or().eq("singer_id",key);
        }
        IPage<SingerEntity> page = this.page(
                new Query<SingerEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SingerEntity> queryAll() {
        return this.list();
    }


}