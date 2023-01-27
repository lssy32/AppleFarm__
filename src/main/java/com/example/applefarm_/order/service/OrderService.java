package com.example.applefarm_.order.service;

import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
import com.example.applefarm_.order.dto.OrderRequestDto;
import com.example.applefarm_.order.dto.OrderResponseDto;
import com.example.applefarm_.order.entity.OrderStatus;
import com.example.applefarm_.order.entity.Orders;
import com.example.applefarm_.order.repository.OrderRepository;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public void order(OrderRequestDto orderRequestDto, Long customerId) {
        Product product = productRepository.findById(orderRequestDto.getProductId())
                .orElseThrow(() -> new CustomException(ExceptionStatus.Product_IS_NOT_EXIST));
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.USER_IS_NOT_EXIST));
        // 주문시 고객이 결제.
        customer.payForOrder(product.getProductPrice(), orderRequestDto.getQuantity());
        product.subtractQuantity(orderRequestDto.getQuantity());
        Orders order = new Orders(product.getSellerId(), product.getId(), customerId, orderRequestDto.getQuantity());
        orderRepository.save(order);
        userRepository.save(customer);
        productRepository.save(product);
    }
    @Transactional
    public List<OrderResponseDto> getMyOrders(int pageChoice, Long sellerId) {
        Page<Orders> orders = orderRepository.findAllBySellerId(sellerId, pageableSetting(pageChoice));
        if (orders.isEmpty()) throw new CustomException(ExceptionStatus.PAGINATION_IS_NOT_EXIST);
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }

    public List<OrderResponseDto> getMyWaitingOrders(int pageChoice, Long sellerId) {
        Page<Orders> orders = orderRepository.findAllBySellerIdAndOrderStatus(sellerId, OrderStatus.WAITING, pageableSetting(pageChoice));
        if (orders.isEmpty()) throw new CustomException(ExceptionStatus.PAGINATION_IS_NOT_EXIST);
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }

    @Transactional
    public Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(pageChoice - 1, 10, sort);
        return pageable;
    }

    @Transactional
    public void orderCompletionProcessing(Long orderId, Long sellerId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new CustomException(ExceptionStatus.Order_IS_NOT_EXIST));
        User seller = userRepository.findById(sellerId).orElseThrow(() -> new CustomException(ExceptionStatus.USER_IS_NOT_EXIST));
        Product product = productRepository.findById(order.getProductId()).orElseThrow(() -> new CustomException(ExceptionStatus.Product_IS_NOT_EXIST));
        order.validateSellerId(sellerId);
        order.orderCompletionProcessing();
        seller.receivePayment(order.getQuantity(), product.getProductPrice());
        orderRepository.save(order);
        userRepository.save(seller);
    }
    @Transactional
    public void orderCancelingProcessing(Long orderId, Long sellerId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new CustomException(ExceptionStatus.Order_IS_NOT_EXIST));
        User customer = userRepository.findById(order.getCustomerId()).orElseThrow(() -> new CustomException(ExceptionStatus.USER_IS_NOT_EXIST));
        Product product = productRepository.findById(order.getProductId()).orElseThrow(() -> new CustomException(ExceptionStatus.Product_IS_NOT_EXIST));
        order.validateSellerId(sellerId);
        order.orderCancelingProcessing();
        customer.refundPayment(order.getQuantity(), product.getProductPrice());
        product.putQuantityBack(order.getQuantity());
        orderRepository.save(order);
        userRepository.save(customer);
        productRepository.save(product);
    }

}