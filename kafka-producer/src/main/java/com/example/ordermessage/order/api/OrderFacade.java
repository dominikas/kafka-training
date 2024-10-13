package com.example.ordermessage.order.api;

import com.example.ordermessage.order.domain.Order;
import com.example.ordermessage.order.domain.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Producer order endpoint")
class OrderFacade {

    private final OrderService orderService;

    private final RestTemplate restTemplate;

    @GetMapping
    public String hello(){
        return "Hello from producer app!";
    }

    @PostMapping
    @Operation(summary = "Post order")
    public void order(@RequestBody @Valid OrderDto orderDto) {
        log.info("Order came to the producer {}", orderDto);
        Order order = new Order(orderDto.getItem(), orderDto.getCount());
        restTemplate.getForEntity("http://localhost:8085/v1/orders", String.class);
        orderService.sendOrder(order);
    }
}
