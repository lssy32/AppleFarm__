package com.example.applefarm_.registration.dto;

import com.example.applefarm_.registration.entity.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistrationResponseDto {
    private Long id;
    private Long userId;
    private String sellerNickname;
    private String sellerImage;
    private String sellerDetail;
    private Long category;

    public RegistrationResponseDto(Registration registration) {
        this.id = registration.getId();
        this.userId = registration.getUserId();
        this.sellerNickname = registration.getSellerNickname();
        this.sellerImage = registration.getSellerImage();
        this.sellerDetail = registration.getSellerDetail();
        this.category = registration.getCategory();
    }
}
