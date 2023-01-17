package com.example.applefarm_.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "success";
    }

    @PostMapping("/signin")
    public void signin(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.signin(loginRequestDto, response);
    }

}