package club.jming.modules.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.jming.common.utils.PageUtils;
import club.jming.modules.music.entity.AlbumEntity;

import java.util.List;
import java.util.Map;

/**
 * 专辑表
 *
 * @author jmingxie
 * @email 782893110@qq.com
 * @date 2022-04-07 00:29:53
 */
public interface AlbumService extends IService<AlbumEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据singerId返回专辑
     * @param singerId
     * @return
     */
    List<AlbumEntity> listBySingerId(Integer singerId);
}

