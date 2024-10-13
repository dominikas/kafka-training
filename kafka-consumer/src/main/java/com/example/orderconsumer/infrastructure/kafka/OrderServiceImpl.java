package com.example.orderconsumer.infrastructure.kafka;

import com.example.orderconsumer.domain.Order;
import com.example.orderconsumer.domain.OrderService;
import com.example.orderconsumer.infrastructure.persistence.OrderPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
class OrderServiceImpl implements OrderService {

    private final OrderPersistence orderPersistence;

    @Override
    public void saveOrder(Order order) {
        orderPersistence.saveOrder(order);
    }

    @Override
    public Order getOrder(UUID id) {
        return orderPersistence.getOrder(id);
    }
}
