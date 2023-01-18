package com.example.applefarm_.admin.controller;

import com.example.applefarm_.admin.service.AdminServiceImpl;
import com.example.applefarm_.registration.dto.RegistrationResponseDto;
import com.example.applefarm_.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final AdminServiceImpl adminService;

    @GetMapping("/users")
    public List<UserResponseDto> findByCustomerList() {
        return adminService.findCustomerList();
    }

    @GetMapping("/sellers")
    public List<UserResponseDto> findBySellerList() {
        return adminService.findSellerList();
    }

    @PutMapping("/modifideroleCustomer/{id}")
    public void modifideroleCustomer(@PathVariable Long id){
        adminService.modifideroleCustomer(id);
    }

    @PutMapping("/modifideroleSeller/{id}")
    public void modifideroleSeller(@PathVariable Long id){
        adminService.modifideroleSeller(id);
    }

    @GetMapping("/regists")
    public List<RegistrationResponseDto> findRegistrationList() {
        return adminService.findRegistrationList();
    }
}