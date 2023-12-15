package com.example.orderconsumer.infrasructure.kafka;

import com.example.orderconsumer.domain.Order;

class OrderMessageMapper {
    
    public static Order toDomain(OrderDto orderDto){
        return new Order(orderDto.getItem(), orderDto.getCount());
    }
}
