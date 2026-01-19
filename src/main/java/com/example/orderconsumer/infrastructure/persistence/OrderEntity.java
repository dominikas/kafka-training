package com.example.orderconsumer.infrastructure.persistence;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;

@Table(name = "orders")
record OrderEntity(

        @Id
        UUID id,
        String name,
        Integer number
) {
}
