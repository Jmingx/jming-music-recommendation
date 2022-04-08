package club.jming.modules.music.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 歌单表
 * 
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
@Data
@TableName("basic_list")
public class ListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	@TableId
	private Integer listId;
	/**
	 * 歌单名
	 */
	private String listName;
	/**
	 * 创建用户Id
	 */
	private Integer userId;
	/**
	 * 歌单介绍
	 */
	private String listDescription;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 歌单封面地址
	 */
	private String imgAddress;
	/**
	 * 歌单类型
	 */
	private String listType;
	/**
	 * 点击次数
	 */
	private Integer clickNumber;
	/**
	 * 是否展示，逻辑删除
	 */
	private Integer showStatus;

}
