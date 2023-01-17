package com.example.applefarm_.seller.controller;

import com.example.applefarm_.security.user.UserDetailsImpl;
import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.seller.dto.UpdateSellerProfileDto;
import com.example.applefarm_.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    //seller 프로필 수정
    @GetMapping("/sellers/profile")
    public SellerProfileResponseDto getMySellerProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new SellerProfileResponseDto(userDetails.getUser());
    }
    //seller 프로필 조회
    @PutMapping("/sellers/profile")
    public void updateMySellerProfile(@RequestBody @Valid UpdateSellerProfileDto updateSellerProfileDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        sellerService.updateMySellerProfile(updateSellerProfileDto, userDetails.getUsername());
    }
}

