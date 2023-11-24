package com.example.ordermessage.order.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@ToString
@Data
@Getter
@AllArgsConstructor
public class Order {

    private String item;
    private String count;
}
