package club.jming.musicCf.component;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicCf.domain.CfUserSimilarity;
import club.jming.musicCf.service.CfRateService;
import club.jming.musicCf.service.CfUserSimilarityService;
import club.jming.musicCf.utils.RecommendationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-13 21:44
 * FileName: RefushSimilarityTask
 * Description: 更新用户间相似度的定时任务
 */
@Component
@Slf4j
public class RefreshSimilarityTask {

    @Autowired
    private CfRateService cfRateService;

    @Autowired
    private CfUserSimilarityService cfUserSimilarityService;

    /**
     * 上一次任务执行完10s后再执行
     */
    @Scheduled(fixedDelay = 10000)
    public void refresh(){
        List<CfRate> cfRates = this.cfRateService.list();
        Map<Integer, Map<Integer, Double>> dataMap = RecommendationUtil.accountingUserItemRate(cfRates);
        List<CfUserSimilarity> similarityList = RecommendationUtil.calculatePearsonCorrelationCoefficient(dataMap);
        cfUserSimilarityService.updateSimilarity(similarityList);
        log.info("定时任务调度 refresh()");
    }
}
