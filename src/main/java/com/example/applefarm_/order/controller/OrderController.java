package com.example.applefarm_.order.controller;

import com.example.applefarm_.order.dto.OrderRequestDto;
import com.example.applefarm_.order.service.OrderService;
import com.example.applefarm_.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{id}")
    public ResponseEntity order (@PathVariable Long id, int quantity,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.order(id, quantity, userDetails.getUser().getId());
        return ResponseEntity.ok("요청 완료");
    }
}
