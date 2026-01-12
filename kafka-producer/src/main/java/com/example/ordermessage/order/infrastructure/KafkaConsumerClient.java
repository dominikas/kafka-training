package com.example.ordermessage.order.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "kafka-consumer-client")
public interface KafkaConsumerClient {

    @GetMapping(value = "/v1/orders")
    String getOrders();

}
