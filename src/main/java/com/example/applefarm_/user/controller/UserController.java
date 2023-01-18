package com.example.applefarm_.user.controller;

import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.user.dto.UserOrderDto;
import com.example.applefarm_.user.dto.SellerRegistrationDto;
import com.example.applefarm_.security.user.UserDetailsImpl;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import com.example.applefarm_.user.dto.UserProfileResponseDto;
import com.example.applefarm_.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "success";
    }

    @PostMapping("/signin")
    public void signin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.signin(loginRequestDto, response);
    }

    @GetMapping("/customer/profile")
    public UserProfileResponseDto getCustomerProfile(@AuthenticationPrincipal UserDetailsImpl user) {
        return new UserProfileResponseDto(user.getUser());
    }

    @GetMapping("/customer/sellerprofile/{id}")
    public SellerProfileResponseDto getSellerProfile(@PathVariable Long id) {
        return userService.getSellerProfile(id);
    }
//
    @GetMapping("/productlist")
    public ResponseEntity getProductList(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.getProductList(page, size);
    }

    @GetMapping("/sellerlist")
    public ResponseEntity getSellerList(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.getSellerList(page, size);
    }


    @PutMapping("/profile")
    public UserProfileResponseDto editUserProfile (@RequestBody UserProfileRequestDto
    userProfileRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.editUserProfile(userProfileRequestDto, userDetails.getUser().getId());
    }

    @PutMapping("/modifideroleCustomer/{id}")
    public void modifideroleCustomer(@PathVariable Long id){
        userService.modifideroleCustomer(id);
    }

    @PutMapping("/modifideroleSeller/{id}")
    public void modifideroleSeller(@PathVariable Long id){
        userService.modifideroleSeller(id);
    }
}