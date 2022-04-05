package club.jming.musicSpider.service.impl;

import club.jming.musicSpider.component.SpiderKugou;
import club.jming.musicSpider.domain.BasicAlbum;
import club.jming.musicSpider.domain.BasicMusic;
import club.jming.musicSpider.domain.BasicSinger;
import club.jming.musicSpider.dto.KugouMusicDTO;
import club.jming.musicSpider.service.BasicAlbumService;
import club.jming.musicSpider.service.BasicMusicService;
import club.jming.musicSpider.service.BasicSingerService;
import club.jming.musicSpider.service.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class SpiderServiceImpl implements SpiderService {

    @Autowired
    private SpiderKugou spiderKugou;

    @Autowired
    private BasicAlbumService albumService;
    @Autowired
    private BasicMusicService musicService;
    @Autowired
    private BasicSingerService singerService;

    @Override
    public List<KugouMusicDTO> crawl(String url) {
        List<KugouMusicDTO> kugouMusicDTOList = crawlFromUrl(url);
        return kugouMusicDTOList;
    }

    @Override
    public void saveDTO(KugouMusicDTO kugouMusicDTO) {
        BasicMusic music = kugouMusicDTO.getMusic();
        BasicAlbum album = kugouMusicDTO.getAlbum();
        List<BasicSinger> singers = kugouMusicDTO.getSingers();

        //TODO 由于数据库设计问题，只保存一个singer，后面有时间可以添加一个music-singer中间表
        int singerId = singerService.updateAndGetId(singers.get(0));
        music.setSingerId(singerId);

        log.debug("singer id : {}",singerId);

        album.setSingerId(singerId);
        int albumId = albumService.updateAndGetId(album);
        log.debug("album id : {}",albumId);
        music.setAlbumId(albumId);

        musicService.save(music);
        log.debug("dto : {}",kugouMusicDTO);
    }

    private List<KugouMusicDTO> crawlFromUrl(String url) {
        List<KugouMusicDTO> res = null;
        if (StringUtils.isEmpty(url)) {
            res = spiderKugou.parameter();
        } else {
            res = spiderKugou.parameter(url);
        }
        return res;
    }
}
