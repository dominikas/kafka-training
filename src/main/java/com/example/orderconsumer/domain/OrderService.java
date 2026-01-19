package com.example.orderconsumer.domain;

import java.util.UUID;

public interface OrderService {

    void saveOrder(Order order);

    Order getOrder(UUID id);
}
