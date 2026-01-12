package com.example.ordermessage.order.api;

import com.example.ordermessage.order.domain.Order;
import com.example.ordermessage.order.domain.OrderService;
import com.example.ordermessage.order.infrastructure.KafkaConsumerClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Producer order endpoint")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class OrderFacade {

    OrderService orderService;

    KafkaConsumerClient consumer;

    @GetMapping
    String hello() {
        log.info("hello");
        return "Hello from producer app!";
    }

    @PostMapping
    @Operation(summary = "Post order")
    void order(@RequestBody @Valid OrderDto orderDto) {
        log.info("Order came to the producer {}", orderDto);
        Order order = new Order(orderDto.getItem(), orderDto.getCount());
        //consumer.getOrders();
        orderService.sendOrder(order);
    }
}
