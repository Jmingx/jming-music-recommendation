package club.jming.musicCf.service.impl;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicApi.domain.Music;
import club.jming.musicApi.service.RecommendationRPCService;
import club.jming.musicCf.domain.CfUserSimilarity;
import club.jming.musicCf.service.CfRateService;
import club.jming.musicCf.service.CfUserSimilarityService;
import club.jming.musicCf.service.MusicService;
import club.jming.musicCf.utils.Constant;
import club.jming.musicCf.utils.RecommendationUtil;
import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-13 12:32
 * FileName: RecommendationRPCServiceImpl
 * Description: 推荐系统Dubbo调用实现类
 */
@Component
@Service(interfaceClass = RecommendationRPCService.class, version = "1.0.0", timeout = 15000)
@Slf4j
public class RecommendationRPCServiceImpl implements RecommendationRPCService {

    @Autowired
    private CfUserSimilarityService similarityService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private CfRateService cfRateService;

    @Override
    public List<Music> getRecommendationMusic(Integer topK,Integer userId) {
        List<Music> musicList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        if (userId != null){
            log.info("getRecommendationMusic() params : topK:{},userId:{}", topK,userId);
            List<CfRate> cfRates = cfRateService.list();
            List<CfUserSimilarity> originList = similarityService.list();
            log.info("originList:{}",originList);
            List<CfUserSimilarity> cfUserSimilarityList = RecommendationUtil.getSimilarityUser(originList, Constant.TOP_K_USER);
            log.info("cfUserSimilarityList:{}",cfUserSimilarityList);
            Map<Integer, Double> items = RecommendationUtil.getRecommendationItems(cfUserSimilarityList, cfRates,userId);
            log.info("items:{}",items);
            List<Integer> topKItems = RecommendationUtil.getRecommendationTopKItems(items, topK);
            log.info("topKItems:{}",topKItems);
            if (topKItems.size() < topK){
                /**
                 *  1. topKItems.size() < target ? 5. : topKItems;
                 *  2. 查库，获取最高rate target个itemId，补充到topKItems
                 */
//                List<Integer> addList = cfRateService.getTopRate(topK);
//                for (int i = 0; i < topK; i++) {
//                    if (i>=topKItems.size()){
//                        topKItems.add(addList.get(i-topKItems.size()));
//                    }
//                }
            }
            Collection<? extends Music> list = this.musicService.getByIdList(topKItems);
            if (list!=null){
                musicList.addAll(list);
            }
            for (Music music : musicList) {
                sb.append("music-name:"+music.getMusicName()+"\r\n");
            }
            log.info("musicList:{}",sb.toString());
        }else {
            Collection<? extends Music> list = this.musicService.getByIdList(cfRateService.getTopRate(topK));
            if (list != null){
                musicList.addAll(list);
            }
            for (Music music : musicList) {
                sb.append("music-name:"+music.getMusicName()+"\r\n");
            }
            log.info("musicList without userId:{}",sb.toString());
        }
        return musicList;
    }
}
