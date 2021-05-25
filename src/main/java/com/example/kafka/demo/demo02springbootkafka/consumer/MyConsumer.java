package com.example.kafka.demo.demo02springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhouwenxiao
 * @create 2021-05-25 17:57
 */
@Component
public class MyConsumer {
    @KafkaListener(topics = "topic-spring-01")
    public void onMessage(ConsumerRecord<Integer, String> record){
        System.out.println("消费者收到的消息："
                            + record.topic()+"\t"
                            +record.partition()+"\t"
                            +record.offset() + "\t"
                            + record.key() + "\t"
                            + record.value() + "\t"
                            );
    }
}
