package com.example.orderconsumer.infrastructure.kafka;

import com.example.orderconsumer.domain.Order;
import com.example.orderconsumer.domain.OrderService;
import com.example.orderconsumer.infrastructure.persistence.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaOrderListener {

    private final OrderService orderService;
    private final OrderMapper mapper;

    @KafkaListener(topics = "${spring.kafka.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrder(@Payload OrderDto message) {
        log.info("Message received {}", message);
        Order order = mapper.toDomain(message);
        orderService.saveOrder(order);
        log.info("Message processed");
    }
}
