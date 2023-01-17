package com.example.applefarm_.order.service;

import com.example.applefarm_.order.dto.OrderRequestDto;
import com.example.applefarm_.order.dto.OrderResponseDto;
import com.example.applefarm_.order.entity.Orders;
import com.example.applefarm_.order.repository.OrderRepository;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.user.entitiy.User;
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
    @Transactional
    public void order(OrderRequestDto orderRequestDto, Long customerId) {
        Product product = productRepository.findById(orderRequestDto.getProductId()).orElseThrow(
                () -> new IllegalArgumentException("상품 정보가 존재하지 않습니다.")
        );
        Orders order = new Orders(product, customerId, orderRequestDto.getQuantity());
        orderRepository.save(order);
    }

    public List<OrderResponseDto> getMyOrders(int pageChoice, User seller) {
        // 완료 처리가 된 애들도 같이 불러와지는 이슈 -> where절 활용해서 0인 애들만 불러오도록 고민해볼 것. -> 해결 될지도?
        Page<Orders> orders= orderRepository.findAllBySellerIdAndIsDeleted(seller.getId(), 0,pageableSetting(pageChoice));
        List<OrderResponseDto> orderResponseDtoList= orders.stream().map(OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }

    public Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(pageChoice-1, 10, sort);
        return pageable;
    }

    public void orderCompletionProcessing(Long orderId, User user) {
        Orders order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("주문내역이 없습니다."));
        order.validateSellerId(user.getId());
        order.orderCompletionProcessing();
        orderRepository.save(order);
    }
}