package com.example.applefarm_.user.service;

import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.user.dto.UserOrderDto;
import com.example.applefarm_.user.dto.SellerRegistrationDto;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import com.example.applefarm_.user.dto.UserProfileResponseDto;
import com.example.applefarm_.user.entitiy.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    @Transactional
    void signup(SignupRequestDto signupRequestDto);

    @Transactional(readOnly = true)
    void signin(LoginRequestDto loginRequestDto, HttpServletResponse response);

    @Transactional(readOnly = true)
    SellerProfileResponseDto getSellerProfile(Long sellerId);

    @Transactional
    ResponseEntity getProductList(int page, int size);

//    @Transactional
//    ResponseEntity getSellerList(int page, int size);

    @Transactional
    UserProfileResponseDto editUserProfile(UserProfileRequestDto userProfileRequestDto, Long id);


    void modifideroleCustomer(Long id);
    void modifideroleSeller(Long id);
}
