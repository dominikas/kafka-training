package com.example.orderconsumer.api;

import com.example.orderconsumer.domain.OrderService;
import com.example.orderconsumer.infrastructure.persistence.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "v1/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Consumer order controller")
class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @Operation(summary = "Save new order")
    void save(@RequestBody OrderDto orderDto) {
        log.info("Saving order {}", orderDto);
        orderService.saveOrder(orderDto.toDomain());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    OrderDto get(@PathVariable UUID id) {
        log.info("Get order with id {}", id);
        return orderMapper.toDto(orderService.getOrder(id));
    }
}
