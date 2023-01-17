package com.example.applefarm_.order.service;

import com.example.applefarm_.order.dto.OrderRequestDto;
import com.example.applefarm_.order.entity.Order;
import com.example.applefarm_.order.repository.OrderRepository;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    @Transactional
    public void order(Long productId, int quantity, Long userId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("상품 정보가 존재하지 않습니다.")
        );
        Order order = new Order();
        order.makeOrder(productId, userId, quantity);
        orderRepository.save(order);
    }
}
