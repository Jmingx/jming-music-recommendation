package club.jming.modules.music.dao;

import club.jming.modules.music.entity.UserSongRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户歌曲中间表(收藏表)
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Mapper
public interface UserSongRelationDao extends BaseMapper<UserSongRelationEntity> {
	
}
