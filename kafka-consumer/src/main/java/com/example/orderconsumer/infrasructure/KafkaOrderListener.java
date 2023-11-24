package com.example.orderconsumer.infrasructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KafkaOrderListener {

    //todo OrderServiceImpl with saving orders to db

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.topic-name}", partitions = {"0"}))
    public void consumeOrder(@Payload OrderDto message) {
        //todo check deserialization errors
        log.info("Message received {}", message);
    }
}
