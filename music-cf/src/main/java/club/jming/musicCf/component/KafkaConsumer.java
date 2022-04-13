package club.jming.musicCf.component;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicApi.utils.Constant;
import club.jming.musicCf.service.CfRateService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-12 23:59
 * FileName: KafkaConsumer
 * Description: kafka消费者
 */
@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private CfRateService cfRateService;

    @KafkaListener(topics = Constant.CF_TOPIC, groupId = Constant.CF_GROUP)
    public void cfConsumer(ConsumerRecord<String, String> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            CfRate rate = JSON.parseObject((String) msg, CfRate.class);
            log.info("Topic:" + topic + ",打分Message:" + rate);
            cfRateService.recodeRate(rate);
            ack.acknowledge();
        }
    }
}
