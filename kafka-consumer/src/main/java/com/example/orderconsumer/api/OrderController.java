package com.example.orderconsumer.api;

import com.example.orderconsumer.infrasructure.persistence.OrderPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="v1/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
class OrderController {

    private final OrderPersistence orderPersistence;

    @PostMapping
    public void order(@RequestBody OrderDto orderDto) {
        log.info("Getting order from Controller " + orderDto);
        orderPersistence.saveOrder(orderDto.toOrder());
    }

    @GetMapping
    public void get() {
        OrderDto orderDto= new OrderDto("test", "123");
        log.info("Get mapping " + orderDto);
        orderPersistence.saveOrder(orderDto.toOrder());
    }
}
