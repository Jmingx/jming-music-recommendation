package club.jming.modules.music.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.jming.common.utils.PageUtils;
import club.jming.common.utils.Query;

import club.jming.modules.music.dao.UserListRelationDao;
import club.jming.modules.music.entity.UserListRelationEntity;
import club.jming.modules.music.service.UserListRelationService;


@Service("userListRelationService")
public class UserListRelationServiceImpl extends ServiceImpl<UserListRelationDao, UserListRelationEntity> implements UserListRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserListRelationEntity> page = this.page(
                new Query<UserListRelationEntity>().getPage(params),
                new QueryWrapper<UserListRelationEntity>()
        );

        return new PageUtils(page);
    }

}