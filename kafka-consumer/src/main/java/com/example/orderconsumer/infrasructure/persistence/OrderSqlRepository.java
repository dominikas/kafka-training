package com.example.orderconsumer.infrasructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderSqlRepository extends JpaRepository<OrderEntity, Integer> {
}
