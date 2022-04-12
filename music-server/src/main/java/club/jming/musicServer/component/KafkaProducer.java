package club.jming.musicServer.component;

import club.jming.musicApi.domain.CfRate;
import club.jming.musicApi.utils.Constant;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Copyright (C), 2017-2022
 *
 * @author: jmingXie
 * Date: 2022-04-12 16:25
 * FileName: KafkaProducer
 * Description: kafka生产者
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(CfRate rate) {
        String cfRate = JSONObject.toJSONString(rate);
        log.info("准备发送消息为：{}", cfRate);
        //发送消息
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Constant.CF_TOPIC, cfRate);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.error(Constant.CF_TOPIC + " - 生产者 发送消息失败：" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, String> stringObjectSendResult) {
                //成功的处理
                log.info(Constant.CF_TOPIC + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
}
