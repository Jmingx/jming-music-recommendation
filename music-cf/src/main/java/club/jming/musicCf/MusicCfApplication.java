package club.jming.musicCf;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class MusicCfApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicCfApplication.class, args);
    }

}
