package club.jming.musicSpider.service;

import club.jming.musicSpider.domain.BasicAlbum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 78289
* @description 针对表【basic_album(专辑表)】的数据库操作Service
* @createDate 2022-04-04 14:25:53
*/
public interface BasicAlbumService extends IService<BasicAlbum> {

    /**
     * 保存专辑信息，如果专辑不存在，保存并返回主键，否则更新并返回主键
     * @param album
     * @return
     */
    int updateAndGetId(BasicAlbum album);
}
