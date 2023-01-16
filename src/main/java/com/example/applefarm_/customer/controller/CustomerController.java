package com.example.applefarm_.customer.controller;

import com.example.applefarm_.customer.dto.CustomerOrderDto;
import com.example.applefarm_.customer.dto.CustomerProfileRequestDto;
import com.example.applefarm_.customer.dto.CustomerProfileResponseDto;
import com.example.applefarm_.customer.dto.SellerRegistrationDto;
import com.example.applefarm_.customer.repository.CustomerRepository;
import com.example.applefarm_.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/profile")
    public CustomerProfileResponseDto getProfile(@PathVariable Long customerId){ //
        return customerService.getProfile(customerId);
    }

    // 전체 판매상품 목록 : 판매 상품목록을 페이징하며 조회
//    @GetMapping("/salelist")
//    public List<ProductResponseDto> getSaleList(Pageable pageable){ // TODO: ProductRepository, ProductResponseDto 추가
//    }

//      전체 판매자 목록 : 판매자들의 목록을 페이징하며 조회
//    @GetMapping("/sellerlist")
//    public List<ProductResponseDto> getSellerList(Pageable pageable){ // TODO: SellerRepository, SellerResponseDto 추가
//    }
//    }







    @PutMapping("/profile")
    public CustomerProfileResponseDto createProfile(@RequestBody CustomerProfileRequestDto customerProfileRequestDto) {
        return customerService.createProfile(customerProfileRequestDto);
    }

    @PostMapping("/requestform/{id}")
    public ResponseEntity order(@PathVariable Long id, CustomerOrderDto customerOrderDto) {
        customerService.order(id, customerOrderDto);
        return ResponseEntity.ok("요청 완료");
    }

    @PostMapping("/registration")
    public ResponseEntity sellerRegist(SellerRegistrationDto sellerRegistrationDto) {
        customerService.sellerRegist(sellerRegistrationDto);
        return ResponseEntity.ok("판매자 등록요청 완료");
    }
}