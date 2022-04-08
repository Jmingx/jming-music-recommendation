package club.jming.modules.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.jming.common.utils.PageUtils;
import club.jming.modules.music.entity.ListEntity;

import java.util.Map;

/**
 * 歌单表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
public interface ListService extends IService<ListEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

