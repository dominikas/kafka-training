package com.example.orderconsumer.infrastructure.kafka;

import com.example.orderconsumer.domain.Order;

class OrderMessageMapper {
    
    public static Order toDomain(OrderDto orderDto){
        return new Order(orderDto.item(), orderDto.count());
    }
}
