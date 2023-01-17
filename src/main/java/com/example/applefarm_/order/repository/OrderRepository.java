package com.example.applefarm_.order.repository;

import com.example.applefarm_.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}