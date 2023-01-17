package com.example.applefarm_.admin.controller;

import com.example.applefarm_.admin.service.AdminServiceImpl;
import com.example.applefarm_.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminServiceImpl adminrService;

    @GetMapping("/users")
    public List<UserResponseDto> findByCustomerList() {
        return adminrService.findCustomerList();
    }

    @GetMapping("/sellers")
    public List<UserResponseDto> findBySellerList() {
        return adminrService.findSellerList();
    }

    @PutMapping("/modifideroleCustomer/{id}")
    public void modifideroleCustomer(@Validated Long id){
        adminrService.modifideroleCustomer(id);
    }
    @PutMapping("/modifideroleSeller/{id}")
    public void modifideroleSeller(@Validated Long id){
        adminrService.modifideroleSeller(id);
    }


}