package com.example.applefarm_.order.repository;

import com.example.applefarm_.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllBySellerIdAndIsDeleted(Long sellerId, int isDeleted, Pageable pageable);

    Optional<Order> findById(Long orderId);

}