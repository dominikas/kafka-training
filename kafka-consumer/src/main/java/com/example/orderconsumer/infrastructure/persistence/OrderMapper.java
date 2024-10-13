package com.example.orderconsumer.infrastructure.persistence;

import com.example.orderconsumer.api.OrderDto;
import com.example.orderconsumer.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity toEntity(Order order);

    OrderDto toDto(Order order);

    Order toDomain(OrderEntity order);
}
