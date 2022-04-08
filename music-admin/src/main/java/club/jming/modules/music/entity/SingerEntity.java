package club.jming.modules.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 歌手表
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Data
@TableName("basic_singer")
public class SingerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer singerId;
	/**
	 * 歌手名
	 */
	private String singerName;
	/**
	 * 歌手介绍
	 */
	private String singerDescription;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 歌手封面地址
	 */
	private String imgAddress;
	/**
	 * 性别，0表示女，1表示男
	 */
	private Integer gender;
	/**
	 * 点击次数
	 */
	private Integer clickNumber;
	/**
	 * 是否展示，逻辑删除
	 */
	private Integer showStatus;

}
