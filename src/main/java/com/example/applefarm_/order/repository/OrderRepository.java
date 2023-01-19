package com.example.applefarm_.order.repository;

import com.example.applefarm_.order.entity.OrderStatus;
import com.example.applefarm_.order.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Page<Orders> findAllBySellerIdAndOrderStatus(Long sellerId, OrderStatus orderStatus, Pageable pageable);

    Optional<Orders> findById(Long orderId);

}