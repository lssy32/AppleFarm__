package com.example.applefarm_.admin.controller;

import com.example.applefarm_.admin.service.AdminServiceImpl;
import com.example.applefarm_.registration.dto.RegistrationResponseDto;
import com.example.applefarm_.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final AdminServiceImpl adminService;

    @GetMapping("/users/{pageChoice}")
    public List<UserResponseDto> findByCustomerList(@PathVariable int pageChoice) {
        return adminService.findCustomerList(pageChoice);
    }

    @GetMapping("/sellers/{pageChoice}")
    public List<UserResponseDto> findBySellerList(@PathVariable int pageChoice) {
        return adminService.findSellerList(pageChoice);
    }

    @PutMapping("/modifideroleCustomer/{id}")
    public void modifiedRoleCustomer(@PathVariable Long id){
        adminService.modifiedRoleCustomer(id);
    }

    @PutMapping("/modifideroleSeller/{id}")
    public void modifiedRoleSeller(@PathVariable Long id){
        adminService.modifiedRoleSeller(id);
    }

    @GetMapping("/regists")
    public List<RegistrationResponseDto> findRegistrationList() {
        return adminService.findRegistrationList();
    }
}