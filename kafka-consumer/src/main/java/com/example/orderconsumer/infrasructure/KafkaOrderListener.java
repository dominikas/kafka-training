package com.example.orderconsumer.infrasructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class KafkaOrderListener {

    //todo OrderServiceImpl zeby tu zapisac message w db

    @KafkaListener(topicPartitions = @TopicPartition(topic = "test_topic",
            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "0")}))
    public void consumeOrder(String message) {
        log.info("Message received {}", message);
    }
}
