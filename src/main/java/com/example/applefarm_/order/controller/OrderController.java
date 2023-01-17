package com.example.applefarm_.order.controller;

import com.example.applefarm_.order.dto.OrderRequestDto;
import com.example.applefarm_.order.dto.OrderResponseDto;
import com.example.applefarm_.order.service.OrderService;
import com.example.applefarm_.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("users/orders")
    public ResponseEntity<String> order (@RequestBody OrderRequestDto orderRequestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.order(orderRequestDto, userDetails.getUser().getId());
        return new ResponseEntity<>("주문 완료", HttpStatus.CREATED);
    }
    @GetMapping("sellers/orders/{pageChoice}")
    public List<OrderResponseDto> getMyOrders (@PathVariable int pageChoice,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails){
        return orderService.getMyOrders(pageChoice, userDetails.getUser());
    }

    @PutMapping("sellers/orders/{orderId}")
    public ResponseEntity<String> orderCompletionProcessing(@PathVariable Long orderId,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        orderService.orderCompletionProcessing(orderId, userDetails.getUser());
        return new ResponseEntity<>("주문요청 처리완료", HttpStatus.OK);
    }

}