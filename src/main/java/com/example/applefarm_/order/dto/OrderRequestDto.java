package com.example.applefarm_.order.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderRequestDto {
    private final Long productId;
    private final int quantity;
}