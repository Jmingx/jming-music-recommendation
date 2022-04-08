package club.jming.modules.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 歌单、歌曲中间表
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Data
@TableName("basic_list_song_relation")
public class ListSongRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer listSongRelationId;
	/**
	 * 歌单Id
	 */
	private String listId;
	/**
	 * 歌曲Id
	 */
	private Integer songId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否展示，逻辑删除
	 */
	private Integer showStatus;

}
