package com.example.ordermessage.order.infrastructure;

import com.example.ordermessage.order.domain.Order;
import com.example.ordermessage.order.domain.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class OrderProducer implements OrderService {

    @Value("${kafka.order.topic.name}")
    private String orderTopicName;

    private final KafkaTemplate kafkaTemplate;

    @Override
    public void sendOrder(Order order) {
        log.info("Sending message to topic {}", orderTopicName);
        kafkaTemplate.send(orderTopicName, "my test message");
    }
}
