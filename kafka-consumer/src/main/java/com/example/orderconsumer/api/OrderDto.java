package com.example.orderconsumer.api;

import com.example.orderconsumer.domain.Order;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
class OrderDto {

    private String item;
    private String count;

    public Order toOrder(){
        return new Order(item, count);
    }
}

