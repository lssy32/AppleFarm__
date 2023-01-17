package com.example.applefarm_.seller.service;

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
        User seller = userRepository.findByLoginId(sellerLoginId).orElseThrow(() -> new IllegalArgumentException("판매자가 존재하지 않습니다."));
        // User 엔티티에 updateSellerProfile 메서드 만들어줘야함.
//        seller.updateSellerProfile(updateSellerProfileDto.getSellerNickname(),
//                updateSellerProfileDto.getSellerImage(),
//                updateSellerProfileDto.getSellerDetails(),
//                updateSellerProfileDto.getSellerCategory());
    }
}
