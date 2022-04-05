package club.jming.musicSpider.controller;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class SpiderController {

    @Autowired
    private SpiderService spiderService;

//  TODO 定义结果类
    @RequestMapping("/crawl")
    public List<KugouMusicDTO> crawl(@RequestParam(value = "url") String url){
        return spiderService.crawl(url);
    }

}
