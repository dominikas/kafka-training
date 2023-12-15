package com.example.orderconsumer.infrasructure.persistence;

import com.example.orderconsumer.domain.Order;

class OrderMapper {

    public static OrderEntity toEntity(Order order){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setItem(order.item());
        orderEntity.setCount(order.count());
        return orderEntity;
    }
}
