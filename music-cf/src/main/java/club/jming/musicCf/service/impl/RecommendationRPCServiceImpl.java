package club.jming.musicCf.service.impl;

import club.jming.musicApi.domain.Music;
import club.jming.musicApi.service.RecommendationRPCService;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-13 12:32
 * FileName: RecommendationRPCServiceImpl
 * Description: 推荐系统Dubbo调用实现类
 */
@Component
@Service(interfaceClass = RecommendationRPCService.class,version = "1.0.0",timeout = 15000)
@Slf4j
public class RecommendationRPCServiceImpl implements RecommendationRPCService {

    @Override
    public List<Music> getRecommendationMusic(Integer topK) {
        log.info("getRecommendationMusic() params : {}",topK);
        return null;
    }
}
