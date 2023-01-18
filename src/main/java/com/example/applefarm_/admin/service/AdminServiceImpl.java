package com.example.applefarm_.admin.service;


import com.example.applefarm_.user.dto.UserResponseDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.applefarm_.user.entitiy.UserRoleEnum.CUSTOMER;
import static com.example.applefarm_.user.entitiy.UserRoleEnum.SELLER;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;


    @Override
    public List<UserResponseDto> findCustomerList() throws IllegalArgumentException {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> customerResult = new ArrayList<>();
        for (User user: users) {
            customerResult.add(new UserResponseDto(user));
        }
        return customerResult;
    }
    @Override
    public List<UserResponseDto> findSellerList() throws IllegalArgumentException {
        List<User> sellers = userRepository.findAll();
        List<UserResponseDto> sellerResult = new ArrayList<>();
        for (User seller : sellers) {
            sellerResult.add(new UserResponseDto(seller));
        }
        return sellerResult;
    }


}