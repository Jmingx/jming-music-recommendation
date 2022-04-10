package club.jming.musicServer.service;


import club.jming.musicServer.domain.ListMusic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ListMusicService extends IService<ListMusic> {

    boolean addListMusic(ListMusic listMusic);

    boolean updateListMusicMsg(ListMusic listMusic);

    boolean deleteListMusic(Integer MusicId);

    List<ListMusic> allListMusic();

    List<ListMusic> listMusicOfMusicId(Integer MusicListId);
}
