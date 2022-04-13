package club.jming.musicCf;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("club.jming.musicCf.mapper")
/**
 * 开启定时任务
 */
@EnableScheduling
public class MusicCfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicCfApplication.class, args);
    }

}
