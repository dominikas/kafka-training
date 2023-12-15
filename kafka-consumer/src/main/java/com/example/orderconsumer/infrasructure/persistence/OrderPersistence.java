package com.example.orderconsumer.infrasructure.persistence;

import com.example.orderconsumer.domain.Order;
import com.example.orderconsumer.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderPersistence implements OrderRepository {

    private final OrderSqlRepository orderSqlRepository;

    @Override
    @Transactional
    public void saveOrder(Order o) {
        OrderEntity orderEntity = OrderMapper.toEntity(o);
        log.info("Saving order to database - start {}", orderEntity);
        orderSqlRepository.save(orderEntity);
        log.info("Saving order to database - success {}", orderEntity);
    }
}
