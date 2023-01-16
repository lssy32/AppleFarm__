package com.example.applefarm_.customer.service;

import com.example.applefarm_.customer.dto.CustomerOrderDto;
import com.example.applefarm_.customer.dto.CustomerProfileRequestDto;
import com.example.applefarm_.customer.dto.CustomerProfileResponseDto;
import com.example.applefarm_.customer.dto.SellerRegistrationDto;
import com.example.applefarm_.customer.entity.Customer;
import com.example.applefarm_.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerProfileResponseDto getProfile(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new IllegalArgumentException("고객 정보가 존재하지 않습니다.")
        );
        CustomerProfileResponseDto customerProfileResponseDto = new CustomerProfileResponseDto(customer);
        return customerProfileResponseDto;
    }





    @Transactional
    public CustomerProfileResponseDto createProfile(CustomerProfileRequestDto customerProfileRequestDto) {
        Customer customer = new Customer(customerProfileRequestDto);
        customerRepository.save(customer);
        return new CustomerProfileResponseDto(customer);
    }

    @Transactional
    public void order(Long id, CustomerOrderDto customerOrderDto) {
        // TODO: 판매자에게 전달하는 기능 추가
    }

    @Transactional
    public void sellerRegist(SellerRegistrationDto sellerRegistrationDto) {
        // TODO: 판매자 레포지토리에 저장
    }
}
