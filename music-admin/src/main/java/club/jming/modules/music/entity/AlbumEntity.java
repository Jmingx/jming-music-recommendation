package club.jming.modules.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 专辑表
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Data
@TableName("basic_album")
public class AlbumEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer albumId;
	/**
	 * 专辑名
	 */
	private String albumName;
	/**
	 * 歌手Id
	 */
	private Integer singerId;
	/**
	 * 专辑介绍
	 */
	private String albumDescription;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 封面地址
	 */
	private String imgAddress;
	/**
	 * 点击次数
	 */
	private Integer clickNumber;
	/**
	 * 是否展示，逻辑删除
	 */
	private Integer showStatus;

}
