package com.example.orderconsumer.infrastructure.persistence;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OrderSqlRepository extends ListCrudRepository<OrderEntity, UUID> {
}
