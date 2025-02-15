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

    @Value("${spring.kafka.template.default-topic}")
    private String orderTopicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Override
    public void sendOrder(Order order) {
        log.info("Sending message to topic {}", orderTopicName);
        try {
            OrderEvent event = OrderEvent.builder()
                    .count(order.getCount())
                    .item(order.getItem())
                    .build();
            kafkaTemplate.send(orderTopicName, event);
            log.info("Message sent {}", event);
        } catch (Exception e) {
            log.error("Error sending message to the broker ", e);
        }
    }
}
