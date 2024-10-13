package com.example.orderconsumer.infrastructure.persistence;

import com.example.orderconsumer.domain.Order;
import com.example.orderconsumer.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderPersistence implements OrderRepository {

    private final OrderSqlRepository orderSqlRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void saveOrder(Order order) {
        final OrderEntity orderEntity = orderMapper.toEntity(order);
        final OrderEntity saved = orderSqlRepository.save(orderEntity);
        log.info("Order saved {}", saved);
    }

    @Override
    public Order getOrder(UUID orderId) {
       return orderSqlRepository.findById(orderId)
                .map(orderMapper::toDomain)
                .orElseThrow();
    }
}
