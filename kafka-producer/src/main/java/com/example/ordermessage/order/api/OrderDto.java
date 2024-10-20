package com.example.ordermessage.order.api;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
class OrderDto {

    @NotEmpty
    private String item;
    @NotEmpty
    private String count;
}
