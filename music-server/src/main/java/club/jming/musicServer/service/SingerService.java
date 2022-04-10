package club.jming.musicServer.service;

import club.jming.musicServer.domain.Singer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SingerService extends IService<Singer> {

    boolean addSinger (Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerPic(Singer singer);

    boolean deleteSinger(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);

    Integer getIdByName(String name);
}
