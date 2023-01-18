package com.example.applefarm_.admin.service;


import com.example.applefarm_.registration.entity.Registration;
import com.example.applefarm_.registration.repository.RegistrationRepository;
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
    private final RegistrationRepository registrationRepository;


    @Override
    @Transactional
    public List<UserResponseDto> findCustomerList() throws IllegalArgumentException {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> customerResult = new ArrayList<>();
        for (User user: users) {
            customerResult.add(new UserResponseDto(user));
        }
        return customerResult;
    }
    @Override
    @Transactional
    public List<UserResponseDto> findSellerList() throws IllegalArgumentException {
        List<User> sellers = userRepository.findAll();
        List<UserResponseDto> sellerResult = new ArrayList<>();
        for (User seller : sellers) {
            sellerResult.add(new UserResponseDto(seller));
        }
        return sellerResult;
    }

    @Override
    @Transactional
    public void modifideroleCustomer(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(user.getRole() == CUSTOMER){
            Registration registration = registrationRepository.findByUserId(id).orElseThrow(
                    () -> new IllegalArgumentException("판매자 등록 요청이 존재하지 않습니다.")
            );
            user.changeSellerByCustomer(registration);
        }else {
            throw new IllegalArgumentException("현재 사용자는 Customer가 아닙니다.");
        }
    }
    @Override
    @Transactional
    public void modifideroleSeller(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(user.getRole() == SELLER){
            user.changeCustomerBySeller();
        }else {throw new IllegalArgumentException("현재 사용자는 Seller가 아닙니다.");}
    }
}