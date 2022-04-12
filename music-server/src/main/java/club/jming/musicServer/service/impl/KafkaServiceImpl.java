package club.jming.musicServer.service.impl;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicServer.component.KafkaProducer;
import club.jming.musicServer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-12 16:40
 * FileName: KafkaServiceImpl
 * Description: KafkaService实现类
 */
@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void sendRate(CfRate rate) {
        kafkaProducer.send(rate);
    }
}
