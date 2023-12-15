package com.example.orderconsumer.infrasructure.kafka;

import com.example.orderconsumer.domain.Order;
import com.example.orderconsumer.domain.OrderService;
import com.example.orderconsumer.infrasructure.persistence.OrderPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class OrderServiceImpl implements OrderService {

    private final OrderPersistence orderPersistence;

    @Override
    public void saveOrder(Order order) {
        log.info("Getting order from Kafka " + order);
        orderPersistence.saveOrder(order);
        log.info("End saving process " + order);
    }
}
