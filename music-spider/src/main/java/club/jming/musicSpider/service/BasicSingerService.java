package club.jming.musicSpider.service;

import club.jming.musicSpider.domain.BasicSinger;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 78289
* @description 针对表【basic_singer(歌手表)】的数据库操作Service
* @createDate 2022-04-04 14:25:53
*/
public interface BasicSingerService extends IService<BasicSinger> {
    /**
     * 保存歌曲信息，如果歌曲不存在，保存并返回主键，否则更新并返回主键
     * @param basicSinger
     * @return
     */
    int updateAndGetId(BasicSinger basicSinger);
}
