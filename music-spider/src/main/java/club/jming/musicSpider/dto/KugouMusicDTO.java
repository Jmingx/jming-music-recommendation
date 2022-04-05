package club.jming.musicSpider.dto;

import club.jming.musicSpider.domain.BasicAlbum;
import club.jming.musicSpider.domain.BasicMusic;
import club.jming.musicSpider.domain.BasicSinger;
import lombok.Data;

import java.util.List;

@Data
public class KugouMusicDTO {
    private BasicAlbum album;
    private BasicMusic music;
    private List<BasicSinger> singers;
}
