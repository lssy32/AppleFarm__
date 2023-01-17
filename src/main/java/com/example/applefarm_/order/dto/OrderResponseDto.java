package com.example.applefarm_.order.dto;

import com.example.applefarm_.order.entity.Orders;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private final Long orderId;
    private final Long customerId;
    private final Long productId;
    private final int quantity;

    public OrderResponseDto(Orders order) {
        this.orderId = order.getId();
        this.customerId = order.getCustomerId();
        this.productId = order.getProductId();
        this.quantity = order.getQuantity();
    }
}