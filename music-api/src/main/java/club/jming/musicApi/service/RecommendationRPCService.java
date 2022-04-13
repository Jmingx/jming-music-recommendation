package club.jming.musicApi.service;

import club.jming.musicApi.domain.Music;

import java.util.List;

/**
 * Copyright (C), 2017-2022
 * Author: jmingXie
 * Date: 2022-04-12 14:48
 * FileName: RecommendationRPCService
 * Description: dubbo远程调用接口
 * @author jmingXie
 */
public interface RecommendationRPCService {

    /**
     * 根据协同过滤计算出需要推送的歌曲，
     * 当推荐列表数量 < topK时，用得分最高的补足
     * @param topK 推荐数量
     * @return 推荐列表
     */
    List<Music> getRecommendationMusic(Integer topK,Integer userId);
}
