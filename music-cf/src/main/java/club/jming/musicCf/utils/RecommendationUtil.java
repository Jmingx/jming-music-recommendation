package club.jming.musicCf.utils;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicCf.domain.CfUserSimilarity;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-13 14:27
 * FileName: RecommendationUtil
 * Description: UserBase协同过滤工具类
 * 一、计算并保存相似度
 *  0. 查数据库，获取cfRate
 *  1. dataMap = accountingUserItemRate(List<CfRate> cfRates)
 *  2. similarityList = List<CfUserSimilarity> calculatePearsonCorrelationCoefficient(Map<Integer, Map<Integer, Double>> dataMap)
 *  3. 保存或者更新到数据库
 *
 * 二、获取推荐列表
 *  0. 查库获取originList，前端输入topK
 *  1. similarityTopKList = List<CfUserSimilarity> getSimilarityUser(List<CfUserSimilarity> originList, int topK)
 *  2. items = Map<Integer, Double> getRecommendationItems(List<CfUserSimilarity> cfUserSimilarityList, List<CfRate> cfRates)
 *  3. topKItems = List<Integer> getRecommendationTopKItems(Map<Integer, Double> items, int topK)
 *  4. topKItems.size() < target ? 5. : topKItems;
 *  5. 查库，获取最高rate target个itemId，补充到topKItems
 *  6. 查所有topKItem的music，返回前端
 */
@Slf4j
public class RecommendationUtil {

    /**
     * 把cfRates整理成map结构
     * <userId,<musicId,cfRate>>
     *
     * @param cfRates
     * @return 整理后的map结构
     */
    public static Map<Integer, Map<Integer, Double>> accountingUserItemRate(List<CfRate> cfRates) {
        Map<Integer, Map<Integer, Double>> dataMap = new ConcurrentHashMap<>();
        for (CfRate rate : cfRates) {
            Integer userId = rate.getUserId();
            Integer musicId = rate.getMusicId();
            Double cfRate = rate.getCfRate();
            if (!dataMap.containsKey(userId)) {
                dataMap.put(userId, new ConcurrentHashMap<>());
            }
            dataMap.get(userId).put(musicId, cfRate);
        }
        return dataMap;
    }

    /**
     * 计算用户间的皮尔逊相关系数
     *
     * @param dataMap
     * @return cfUserSimilarity列表
     */
    public static List<CfUserSimilarity> calculatePearsonCorrelationCoefficient(Map<Integer, Map<Integer, Double>> dataMap) {
        List<CfUserSimilarity> similarityList = new LinkedList<>();
        if (dataMap.size() < 2) {
            return similarityList;
        }
        //保存平均值<userId,avlOfCfRate>
        Map<Integer, Double> avlMap = new HashMap<>();
        for (Integer userId : dataMap.keySet()) {
            int size = dataMap.get(userId).size();
            if (size < 1) {
                continue;
            }
            Double total = 0d;
            for (Double cfRate : dataMap.get(userId).values()) {
                total += cfRate;
            }
            avlMap.put(userId, total / size);
        }
        //获取用户列表
        List<Integer> userList = new LinkedList<>(dataMap.keySet());
        //计算用户间相似度
        for (Integer userId : userList) {
            for (Integer userRedId : userList) {
                if (userId.equals(userRedId)) {
                    continue;
                }
                Map<Integer, Double> userMap = dataMap.get(userId);
                Map<Integer, Double> userRefMap = dataMap.get(userRedId);

                //user平均值
                double userAvl = avlMap.get(userId);
                //userRef平均值
                double userRefAvl = avlMap.get(userRedId);
                //相似度
                double similarity = 0d;
                // 余弦相似度公式中的分子
                double molecule = 0d;
                // 余弦相似度公式中的分母
                double denominator = 1d;
                // 余弦相似度公式中分母根号下的两个向量的模的值
                double vector1 = 0d;
                double vector2 = 0d;
                // 得分-平均得分
                double tmp = 0d;
                double tmpRef = 0d;

                Set<Integer> itemSet = new ConcurrentHashSet<>();
                itemSet.addAll(userMap.keySet());
                itemSet.addAll(userRefMap.keySet());
                for (Integer itemId : itemSet) {
                    tmp = userMap.getOrDefault(itemId, userAvl) - userAvl;
                    tmpRef = userRefMap.getOrDefault(itemId, userAvl) - userRefAvl;
                    molecule += tmp * tmpRef;
                    vector1 += Math.pow(tmp, 2);
                    vector2 += Math.pow(tmpRef, 2);
                }
                denominator = Math.sqrt(vector1) * Math.sqrt(vector2);
                similarity = denominator == 0d ? 0d : molecule / denominator;

                CfUserSimilarity cfUserSimilarity = new CfUserSimilarity();
                cfUserSimilarity.setSimilarity(similarity);
                cfUserSimilarity.setUserId(userId);
                cfUserSimilarity.setUserRefId(userRedId);
                similarityList.add(cfUserSimilarity);
            }
        }
        return similarityList;
    }

    /**
     * 根据输出的 originList 计算出与之最相似的 topK 个用户
     * 计算userId最相似的topK个用户
     *
     * @param originList 输入的list，所有entity.getUserId必须都相同
     * @param topK
     * @return
     */
    public static List<CfUserSimilarity> getSimilarityUser(List<CfUserSimilarity> originList, int topK) {
        List<CfUserSimilarity> similarityTopKList = new LinkedList<>();
        //小顶堆获取前topK个相似用户
        PriorityQueue<CfUserSimilarity> queue = new PriorityQueue<>(topK, new Comparator<CfUserSimilarity>() {
            @Override
            public int compare(CfUserSimilarity o1, CfUserSimilarity o2) {
                return o1.getSimilarity() - o2.getSimilarity() >= 0 ? 1 : -1;
            }
        });
        for (CfUserSimilarity similarity : originList) {
            //保证第topK大不被移除
            if (queue.size() == topK + 1) {
                queue.poll();
            }
            queue.offer(similarity);
        }
        //移除第topK+1大个元素
        queue.poll();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            similarityTopKList.add(queue.poll());
        }
        return similarityTopKList;
    }

    /**
     * 计算userId的预测评分表
     *
     * @param cfUserSimilarityList
     * @param cfRates
     * @param userId 需要推荐的用户
     * @return <itemId,rate> 返回可推荐items的评分列表
     */
    public static Map<Integer, Double> getRecommendationItems(List<CfUserSimilarity> cfUserSimilarityList, List<CfRate> cfRates,Integer userId) {
        Map<Integer, Double> items = new ConcurrentHashMap<>();
        Map<Integer, Map<Integer, Double>> dataMap = accountingUserItemRate(cfRates);
        //保存平均值<userId,avlOfCfRate>
        Map<Integer, Double> avlMap = new HashMap<>();
        for (Integer uId : dataMap.keySet()) {
            int size = dataMap.get(uId).size();
            if (size < 1) {
                continue;
            }
            Double total = 0d;
            for (Double cfRate : dataMap.get(uId).values()) {
                total += cfRate;
            }
            avlMap.put(uId, total / size);
        }

        //1. 把当前用户的打分加入items
        if (dataMap.containsKey(userId) && dataMap.get(userId).size()>0){
            for (Integer itemId : dataMap.get(userId).keySet()) {
                items.put(itemId, dataMap.get(userId).get(itemId));
            }
        }

        log.info("当前用户的打分列表:{}",items);
        log.info("dataMap:{}",dataMap);

        //2. 获取所有相关itemId
        Set<Integer> itemList = new ConcurrentHashSet<>();
        for (CfUserSimilarity cfUserSimilarity : cfUserSimilarityList) {
            Integer userRefId = cfUserSimilarity.getUserRefId();
            itemList.addAll(dataMap.get(userRefId).keySet());
        }

        // 预测的评分
        double predictRate = 0d;
        // 公式中的分子
        double molecule = 0d;
        // 公式中的分母
        double denominator = 0d;
        // 相似度
        double similarity = 0d;
        // user平均分
        double userAvlRate = avlMap.getOrDefault(userId,0d);
        // userRef平均分
        double userRefAvlRate = 0d;

        //3. 计算itemList中，但不在items里面的item的打分
        for (Integer its : itemList) {
            if (!items.containsKey(its)) {
                //计算预测评分
                for (CfUserSimilarity cfs : cfUserSimilarityList) {
                    Integer userRefId = cfs.getUserRefId();
                    userRefAvlRate = avlMap.get(userRefId);
                    similarity = cfs.getSimilarity();
                    molecule += similarity;
                    denominator += similarity * (dataMap.get(userRefId).getOrDefault(its,0d) - userRefAvlRate);
                }
                predictRate = userAvlRate + molecule / denominator;
                items.put(its, predictRate);
            }
        }
        return items;
    }

    /**
     * 返回推荐评分topK个推荐item
     *
     * @param items
     * @param topK
     * @return
     */
    public static List<Integer> getRecommendationTopKItems(Map<Integer, Double> items, int topK) {
        List<Integer> topKItems = new LinkedList<>();
        Set<Map.Entry<Integer, Double>> entries = items.entrySet();
        PriorityQueue<Map.Entry<Integer, Double>> queue = new PriorityQueue<>(topK, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getValue() - o2.getValue() > 0 ? -1 : 1;
            }
        });
        for (Map.Entry<Integer, Double> entry : entries) {
            if (queue.size() == topK + 1){
                queue.poll();
            }
            queue.offer(entry);
        }
        //移除第topK+1大个元素
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            //插入头部，顺序排序
            topKItems.add(0,queue.poll().getKey());
        }

        return topKItems;
    }
}
