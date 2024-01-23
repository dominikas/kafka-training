package com.example.ordermessage.order.api;

import com.example.ordermessage.order.domain.Order;
import com.example.ordermessage.order.domain.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
class OrderFascade {

    private final OrderService orderService;

    private final RestTemplate restTemplate;

    @PostMapping
    public void order(@RequestBody @Valid OrderDto orderDto) {
        log.info("Request is here " + orderDto);
        Order order = new Order(orderDto.getItem(), orderDto.getCount());
        log.info("TESSSSSSSST");
        restTemplate.getForEntity("http://localhost:8085/v1/order", String.class);
        orderService.sendOrder(order);
    }
}
