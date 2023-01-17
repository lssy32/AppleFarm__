package com.example.applefarm_.user.dto;

import com.example.applefarm_.user.entitiy.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String loginId;
    private UserRoleEnum roleEnum;

}