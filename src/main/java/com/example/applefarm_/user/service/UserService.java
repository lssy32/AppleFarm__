package com.example.applefarm_.user.service;

import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    @Transactional
    void signup(SignupRequestDto signupRequestDto);

    @Transactional(readOnly = true)
    void signin(LoginRequestDto loginRequestDto, HttpServletResponse response);


}
