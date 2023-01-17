package com.example.applefarm_.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserProfileRequestDto {
    private String nickname;
    private String image;

}