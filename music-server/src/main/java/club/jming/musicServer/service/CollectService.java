package club.jming.musicServer.service;

import club.jming.musicServer.domain.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CollectService extends IService<Collect> {

    boolean addCollection(Collect collect);

    boolean existMusicId(Integer userId, Integer MusicId);

    boolean updateCollectMsg(Collect collect);

    boolean deleteCollect(Integer userId, Integer MusicId);

    List<Collect> allCollect();

    List<Collect> collectionOfUser(Integer userId);
}
