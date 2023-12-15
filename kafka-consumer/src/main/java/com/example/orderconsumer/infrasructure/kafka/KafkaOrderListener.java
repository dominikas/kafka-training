package com.example.orderconsumer.infrasructure.kafka;

import com.example.orderconsumer.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaOrderListener {

    private final OrderServiceImpl orderService;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${kafka.topic-name}", partitions = {"0"}))
    public void consumeOrder(@Payload OrderDto message) {
        log.info("Message received {}", message);
        Order order = OrderMessageMapper.toDomain(message);
        orderService.saveOrder(order);
        log.info("Message processed {}", message);
    }
}
