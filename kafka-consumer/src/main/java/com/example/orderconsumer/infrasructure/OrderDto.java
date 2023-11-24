package com.example.orderconsumer.infrasructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class OrderDto {
    private String item;
    private Integer count;
}
