package club.jming.modules.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户歌单中间表(收藏歌单表)
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Data
@TableName("basic_user_list_relation")
public class UserListRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer userListRelationId;
	/**
	 * 用户Id
	 */
	private Integer userListId;
	/**
	 * 歌曲Id
	 */
	private Integer userId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否展示，逻辑删除
	 */
	private Integer showStatus;

}
