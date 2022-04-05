package club.jming.musicSpider.service;

import club.jming.musicSpider.dto.KugouMusicDTO;

import java.util.List;

public interface SpiderService {

    /**
     * 爬取数据
     * @param url
     * @return
     */
    List<KugouMusicDTO> crawl(String url);

    /**
     * 保存单个DTO
     * @param kugouMusicDTO
     */
    void saveDTO(KugouMusicDTO kugouMusicDTO);
}
