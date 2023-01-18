package com.example.applefarm_.seller.service;

import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
import com.example.applefarm_.seller.dto.UpdateSellerProfileDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final UserRepository userRepository;
    @Transactional
    public void updateMySellerProfile(UpdateSellerProfileDto updateSellerProfileDto, String sellerLoginId) {
        User seller = userRepository.findByLoginId(sellerLoginId).orElseThrow(() -> new CustomException(ExceptionStatus.Seller_IS_NOT_EXIST));

        seller.updateSellerProfile(updateSellerProfileDto.getSellerNickname(),
                updateSellerProfileDto.getSellerImage(),
                updateSellerProfileDto.getSellerDetails(),
                updateSellerProfileDto.getSellerCategory());
    }
}
