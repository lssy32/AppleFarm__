package com.example.applefarm_.registration.entity;

import com.example.applefarm_.registration.dto.RegistrationRequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String sellerNickname;
    private String sellerImage;
    private String sellerDetail;
    private Long category;

    public Registration(RegistrationRequestDto registrationRequestDto, Long id) {
        this.userId = id;
        this.sellerNickname = registrationRequestDto.getSellerNickname();
        this.sellerImage = registrationRequestDto.getSellerImage();
        this.sellerDetail = registrationRequestDto.getSellerDetail();
        this.category = registrationRequestDto.getCategory();
    }
}