package com.example.applefarm_.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    @NotNull
    private String loginId;
    @NotNull
    private String loginPassword;
}
