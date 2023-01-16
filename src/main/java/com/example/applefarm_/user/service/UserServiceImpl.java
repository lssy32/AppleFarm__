package com.example.applefarm_.user.service;


import com.example.applefarm_.security.jwt.JwtUtil;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void signup(SignupRequestDto signupRequestDto) throws IllegalArgumentException {
        String loginId = signupRequestDto.getLoginId();
        String loginPassword = passwordEncoder.encode(signupRequestDto.getLoginPassword());
        // 회원 중복 확인
        Optional<User> found = userRepository.findByLoginId(loginId);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // 사용자 가입
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        User user = new User(loginId, loginPassword, role);
        userRepository.save(user);
    }

    @Override
    public void signin(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String loginId = loginRequestDto.getLoginId();
        // 사용자 확인
        User user = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if (!passwordEncoder.matches(loginRequestDto.getLoginPassword(), user.getLoginPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId(), user.getRole()));
    }

}