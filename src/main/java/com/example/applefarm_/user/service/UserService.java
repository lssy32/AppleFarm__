package com.example.applefarm_.user.service;

import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import com.example.applefarm_.user.dto.UserProfileResponseDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    void signup(SignupRequestDto signupRequestDto);


    void signin(LoginRequestDto loginRequestDto, HttpServletResponse response);

    SellerProfileResponseDto getSellerProfile(Long id);


    ResponseEntity getProductList(int page, int size);


    ResponseEntity getSellerList(int page, int size);


    UserProfileResponseDto editUserProfile(UserProfileRequestDto userProfileRequestDto, Long id);
    void signout(HttpServletRequest request);

}
