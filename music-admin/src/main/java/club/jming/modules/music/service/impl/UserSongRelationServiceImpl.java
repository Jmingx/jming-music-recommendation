package club.jming.modules.music.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.UserSongRelationDao;
import club.jming.modules.music.entity.UserSongRelationEntity;
import club.jming.modules.music.service.UserSongRelationService;


@Service("userSongRelationService")
public class UserSongRelationServiceImpl extends ServiceImpl<UserSongRelationDao, UserSongRelationEntity> implements UserSongRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserSongRelationEntity> page = this.page(
                new Query<UserSongRelationEntity>().getPage(params),
                new QueryWrapper<UserSongRelationEntity>()
        );

        return new PageUtils(page);
    }

}