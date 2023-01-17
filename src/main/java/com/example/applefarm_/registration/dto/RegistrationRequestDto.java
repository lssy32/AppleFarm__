package com.example.applefarm_.registration.dto;

import lombok.Getter;

@Getter
public class RegistrationRequestDto {
    private String sellerNickname;
    private String sellerImage;
    private String sellerDetail;
    private Long category;
}
