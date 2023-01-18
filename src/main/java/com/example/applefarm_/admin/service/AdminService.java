package com.example.applefarm_.admin.service;

import com.example.applefarm_.user.dto.UserResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminService {

    List<UserResponseDto> findCustomerList();


    List<UserResponseDto> findSellerList();

    void modifideroleCustomer(Long id);

    void modifideroleSeller(Long id);

}
