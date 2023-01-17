package com.example.applefarm_.registration.controller;

import com.example.applefarm_.registration.dto.RegistrationRequestDto;
import com.example.applefarm_.registration.repository.RegistrationRepository;
import com.example.applefarm_.registration.service.RegistrationService;
import com.example.applefarm_.security.user.UserDetailsImpl;
import com.example.applefarm_.user.dto.SellerRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regist")
public class RegistraionController {
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity sellerRegist(RegistrationRequestDto registrationRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        registrationService.sellerRegist(registrationRequestDto, userDetails.getUser().getId());
        return ResponseEntity.ok("판매자 등록요청 완료");
    }
}