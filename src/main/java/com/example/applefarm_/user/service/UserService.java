package com.example.applefarm_.user.service;

import com.example.applefarm_.product.dto.ProductResponse;
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
import java.util.List;

public interface UserService {

    void signup(SignupRequestDto signupRequestDto);


    void signin(LoginRequestDto loginRequestDto, HttpServletResponse response);

    SellerProfileResponseDto getSellerProfile(Long id);


    ResponseEntity getProductList(int page, int size);


    ResponseEntity getSellerList(int page, int size);


    UserProfileResponseDto editUserProfile(UserProfileRequestDto userProfileRequestDto, Long id);
    void signout(LoginRequestDto loginRequestDto, HttpServletResponse response);

    List<ProductResponse> getProductsByKeyword(String keyword, int page);

    List<ProductResponse> getProductsByNickname(String nickname, int page);
}
