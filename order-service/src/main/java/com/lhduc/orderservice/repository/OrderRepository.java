package com.lhduc.orderservice.repository;

import com.lhduc.orderservice.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
