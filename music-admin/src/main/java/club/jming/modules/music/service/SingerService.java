package club.jming.modules.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.jming.common.utils.PageUtils;
import club.jming.modules.music.entity.SingerEntity;

import java.util.List;
import java.util.Map;

/**
 * 歌手表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
public interface SingerService extends IService<SingerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SingerEntity> queryAll();
}

