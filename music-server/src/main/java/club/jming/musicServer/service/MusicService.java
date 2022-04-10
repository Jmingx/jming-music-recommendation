package club.jming.musicServer.service;


import club.jming.musicServer.domain.Music;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MusicService extends IService<Music> {

    boolean addMusic (Music Music);

    boolean updateMusicMsg(Music Music);

    boolean updateMusicUrl(Music Music);

    boolean updateMusicPic(Music Music);

    boolean deleteMusic(Integer id);

    List<Music> allMusic();

    List<Music> musicOfSingerId(Integer singerId);

    List<Music> musicOfId(Integer id);

    List<Music> musicOfSingerName(String name);

    List<Music> musicOfName(String name);
}
