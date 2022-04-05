package club.jming.musicSpider;

import club.jming.musicSpider.service.BasicMusicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicSpiderApplicationTest {
    @Autowired
    private BasicMusicService basicMusicService;

    @Test
    public void test(){
        System.out.println(basicMusicService.list());
    }
}