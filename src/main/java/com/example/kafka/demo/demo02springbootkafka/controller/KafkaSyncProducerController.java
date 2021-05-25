package com.example.kafka.demo.demo02springbootkafka.controller;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author zhouwenxiao
 * @create 2021-05-25 17:28
 */
@Repository
@RestController
public class KafkaSyncProducerController {
    @Autowired
    private KafkaTemplate<Integer, String> template;
    @RequestMapping("send/sync/{message}")
    public String send(@PathVariable String message) throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<Integer, String>> future= template.send("topic-spring-01", 0, 0, message);
        // 同步发送消息
        SendResult<Integer, String> sendResult = future.get();
        RecordMetadata metadata = sendResult.getRecordMetadata();

        System.out.println(metadata.topic()+"\t" + metadata.partition()+ "\t" + metadata.offset());
        return "success";
    }

}
