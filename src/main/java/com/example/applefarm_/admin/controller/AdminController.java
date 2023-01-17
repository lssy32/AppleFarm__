package com.example.applefarm_.admin.controller;

import com.example.applefarm_.admin.service.AdminServiceImpl;
import com.example.applefarm_.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public void modifideroleCustomer(@PathVariable Long id){
        adminrService.modifideroleCustomer(id);
    }
    @PutMapping("/modifideroleSeller/{id}")
    public void modifideroleSeller(@PathVariable Long id){
        adminrService.modifideroleSeller(id);
    }


}