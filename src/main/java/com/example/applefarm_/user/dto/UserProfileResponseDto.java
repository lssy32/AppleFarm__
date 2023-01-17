package com.example.applefarm_.user.dto;

import com.example.applefarm_.user.entitiy.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class UserProfileResponseDto {
    private String nickname;
    private String image;

    public UserProfileResponseDto(User user) {
        this.nickname = user.getNickname();
        this.image = user.getImage();
    }
}