package com.example.applefarm_.user.service;


import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.security.jwt.JwtUtil;
import com.example.applefarm_.seller.repository.SellerRepository;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import com.example.applefarm_.user.dto.UserProfileResponseDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
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
        String nickName = signupRequestDto.getNickName();
        String image = signupRequestDto.getImage();
        // 사용자 가입
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        User user = new User(loginId, loginPassword, nickName, image, role);
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


//    @Override
//    public ResponseEntity getProductList(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Product> products = productRepository.findAll(pageable);
//        List<ProductReponseDto> productReponseDtoList = products.stream().map(product -> new ProductReponseDto(product)).collect(Collectors.toList());
//        return new ResponseEntity<>(productReponseDtoList, HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity getSellerList(int page, int size){
//        Pageable pageable = PageRequest.of(page, size);
//        Page<SellerProfile> sellers = sellerRepository.findAll(pageable);
//        List<SellerNicknameResponseDto> productReponseDtoList = sellers.stream().map(seller -> new SellerNicknameResponseDto(seller)).collect(Collectors.toList());
//        return new ResponseEntity<>(productReponseDtoList, HttpStatus.OK);
//    }
//
//    @Override
//    public SellerProfileResponseDto getSellerProfile(Long sellerId){
//        SellerProfile sellerProfile = sellerRepository.findById(sellerId).orElseThrow(
//                () -> new IllegalArgumentException("판매자 정보가 존재하지 않습니다.")
//        );
//        SellerProfileResponseDto sellerProfileResponseDto = new SellerProfileResponseDto(sellerProfile);
//        return sellerProfileResponseDto;
//    }

    @Override
    public UserProfileResponseDto editUserProfile(UserProfileRequestDto userProfileRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("고객 정보가 존재하지 않습니다.")
        );
        user.update(userProfileRequestDto);
        userRepository.save(user);
        return new UserProfileResponseDto(user);
    }
}