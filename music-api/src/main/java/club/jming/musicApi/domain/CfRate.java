package club.jming.musicApi.domain;

import club.jming.musicApi.utils.Constant;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2022
 * Date: 2022-04-12 15:15
 * FileName: CfRate
 * Description: user-item评分表,用于kafka传输,采用json序列化
 *
 * @author jmingXie
 */
@Data
public class CfRate implements Serializable {
    private Integer rateId;

    private Integer userId;

    private Integer musicId;

    /**
     * 评分
     */
    private Double cfRate;

    public CfRate(Integer userId, Integer musicId, Double cfRate) {
        this.userId = userId;
        this.musicId = musicId;
        this.cfRate = cfRate;
    }

    public CfRate() {
    }

    /**
     * 收藏得分
     * @param userId
     * @param musicId
     * @return
     */
    public static CfRate collectedCfRate(Integer userId, Integer musicId) {
        return new CfRate(userId,musicId, Constant.COLLECTED_RATE);
    }

    /**
     * 评论得分
     * @param userId
     * @param musicId
     * @return
     */
    public static CfRate commentCfRate(Integer userId,Integer musicId){
        return new CfRate(userId,musicId,Constant.COMMENT_RATE);
    }

    /**
     * 评论点赞得分
     * @param userId
     * @param musicId
     * @return
     */
    public static CfRate commentLikeCfRate(Integer userId,Integer musicId){
        return new CfRate(userId,musicId,Constant.COMMENT_LIKE_RATE);
    }

    /**
     * 给定分数得分
     * @param userId
     * @param musicId
     * @param cfRate
     * @return
     */
    public static CfRate normalCfRate(Integer userId, Integer musicId, Double cfRate) {
        return new CfRate(userId,musicId,cfRate);
    }

    public static CfRate playCfRate(Integer userId,Integer musicId){
        return new CfRate(userId,musicId,Constant.PLAY_RATE);
    }
}
