package club.jming.musicServer.service;

import club.jming.musicApi.domain.CfRate;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-12 16:39
 * FileName: KafkaService
 * Description: kafka service
 */
public interface KafkaService {

    /**
     * 发送打分
     * @param rate
     */
    void sendRate(CfRate rate);
}
