package com.example.orderconsumer.api;

import com.example.orderconsumer.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
class OrderDto {

    private String item;
    private String count;

    public Order toOrder(){
        return new Order(item, Integer.parseInt(count));
    }
}

