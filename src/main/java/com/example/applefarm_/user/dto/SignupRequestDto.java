package com.example.applefarm_.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String loginId;
    private String loginPassword;
    private String nickName;
    private String image;
}