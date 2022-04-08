package club.jming.modules.music.dao;

import club.jming.modules.music.entity.UserListRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户歌单中间表(收藏歌单表)
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Mapper
public interface UserListRelationDao extends BaseMapper<UserListRelationEntity> {
	
}
