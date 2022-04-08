package club.jming.musicGenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@MapperScan("club.jming.musicGenerator.dao")
public class MusicGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicGeneratorApplication.class, args);
	}
}
