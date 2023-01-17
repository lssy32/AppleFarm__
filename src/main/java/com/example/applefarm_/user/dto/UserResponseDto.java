package com.example.applefarm_.user.dto;

import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String loginId;
    private String nickName;
    private String image;
    private UserRoleEnum role;

    public UserResponseDto(User user){
        this.loginId = user.getLoginId();
        this.nickName = user.getNickName();
        this.image = user.getImage();
        this.role = user.getRole();
    }

}