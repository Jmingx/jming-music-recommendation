package club.jming.musicServer.service;


import club.jming.musicServer.domain.MusicList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MusicListService extends IService<MusicList> {

    boolean addMusicList (MusicList MusicList);

    boolean updateMusicListMsg(MusicList MusicList);

    boolean updateMusicListImg(MusicList MusicList);

    boolean deleteMusicList(Integer id);

    List<MusicList> allMusicList();

    List<MusicList> likeTitle(String title);

    List<MusicList> likeStyle(String style);

    List<MusicList> musicListOfTitle(String title);

}
