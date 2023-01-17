package com.example.applefarm_.admin.service;


import com.example.applefarm_.user.dto.UserResponseDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void modifideroleCustomer(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(user.getRole() == CUSTOMER){
            user.changeSellerByCustomer();
        }else {
            throw new IllegalArgumentException("이 사용자는 Customer가 아닙니다.");
        }
    }
    @Override
    public void modifideroleSeller(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(user.getRole() == SELLER){
            user.changeCustomerBySeller();
        }else {throw new IllegalArgumentException("이 사용자는 Seller가 아닙니다.");}
    }

}