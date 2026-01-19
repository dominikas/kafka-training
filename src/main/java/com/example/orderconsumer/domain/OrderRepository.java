package com.example.orderconsumer.domain;

import java.util.UUID;

public interface OrderRepository {

    void saveOrder(Order order);

    Order getOrder(UUID id);
}
