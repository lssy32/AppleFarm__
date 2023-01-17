package com.example.applefarm_.registration.controller;

import com.example.applefarm_.registration.repository.RegistrationRepository;
import com.example.applefarm_.registration.service.RegistrationService;
import com.example.applefarm_.user.dto.SellerRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regist")
public class RegistraionController {
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity sellerRegist (SellerRegistrationDto sellerRegistrationDto){

        // TODO: 판매자 등록 요청 저장
        return ResponseEntity.ok("판매자 등록요청 완료");
    }
}