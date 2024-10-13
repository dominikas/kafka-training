package com.example.orderconsumer.api;

import com.example.orderconsumer.domain.Order;

public record OrderDto(String name,
                       Integer number) {

    Order toDomain() {
        return new Order(name, number);
    }
}

