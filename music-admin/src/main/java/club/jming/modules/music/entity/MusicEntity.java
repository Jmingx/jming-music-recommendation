package club.jming.modules.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 音乐表
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Data
@TableName("basic_music")
public class MusicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer musicId;
	/**
	 * 歌曲名
	 */
	private String musicName;
	/**
	 * 歌手Id
	 */
	private Integer singerId;
	/**
	 * 专辑id
	 */
	private Integer albumId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 音乐文件地址
	 */
	private String musicAddress;
	/**
	 * 封面地址
	 */
	private String imgAddress;
	/**
	 * 点击次数
	 */
	private Integer clickNumber;
	/**
	 * 音乐类型
	 */
	private String musicType;
	/**
	 * 是否展示，逻辑删除
	 */
	private Integer showStatus;

}
