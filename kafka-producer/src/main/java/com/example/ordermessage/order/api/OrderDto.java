package com.example.ordermessage.order.api;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
class OrderDto {

    @NotEmpty
    private String item;
    @NotEmpty
    private String count;
}
