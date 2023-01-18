package com.example.applefarm_.admin.service;

import com.example.applefarm_.registration.dto.RegistrationRequestDto;
import com.example.applefarm_.registration.dto.RegistrationResponseDto;
import com.example.applefarm_.user.dto.UserResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {

    List<UserResponseDto> findCustomerList(int pageChoice);


    List<UserResponseDto> findSellerList(int pageChoice);

    void modifideroleCustomer(Long id);

    void modifideroleSeller(Long id);

    List<RegistrationResponseDto> findRegistrationList();
}
