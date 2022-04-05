package club.jming.musicSpider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("club.jming.musicSpider.mapper")
//@EnableTransactionManagement
public class MusicSpiderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicSpiderApplication.class, args);
    }

}
