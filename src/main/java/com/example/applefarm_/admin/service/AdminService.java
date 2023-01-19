package com.example.applefarm_.admin.service;

import com.example.applefarm_.registration.dto.RegistrationResponseDto;
import com.example.applefarm_.user.dto.UserResponseDto;

import java.util.List;

public interface AdminService {

    List<UserResponseDto> findCustomerList(int pageChoice);


    List<UserResponseDto> findSellerList(int pageChoice);

    void modifiedRoleCustomer(Long id);

    void modifiedRoleSeller(Long id);

    List<RegistrationResponseDto> findRegistrationList();
}
