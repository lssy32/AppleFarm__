package com.example.applefarm_.seller.controller;

import com.example.applefarm_.security.user.UserDetailsImpl;
import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.seller.dto.UpdateSellerProfileDto;
import com.example.applefarm_.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers")
public class SellerController {
    private final SellerService sellerService;
    //seller 프로필 수정
    @GetMapping("/profile")
    public SellerProfileResponseDto getMySellerProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new SellerProfileResponseDto(userDetails.getUser());
    }
    //seller 프로필 조회
    @PutMapping("/profile")
    public void updateMySellerProfile(@RequestBody @Valid UpdateSellerProfileDto updateSellerProfileDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        sellerService.updateMySellerProfile(updateSellerProfileDto, userDetails.getUsername());
    }
}

