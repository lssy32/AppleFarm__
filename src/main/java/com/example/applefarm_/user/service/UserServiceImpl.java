package com.example.applefarm_.user.service;


import com.example.applefarm_.product.dto.ProductResponse;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.registration.entity.Registration;
import com.example.applefarm_.registration.repository.RegistrationRepository;
import com.example.applefarm_.security.jwt.JwtUtil;
import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.seller.entitiy.SellerProfile;
import com.example.applefarm_.seller.repository.SellerRepository;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import com.example.applefarm_.user.dto.UserProfileResponseDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.applefarm_.user.entitiy.UserRoleEnum.CUSTOMER;
import static com.example.applefarm_.user.entitiy.UserRoleEnum.SELLER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final RegistrationRepository registrationRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
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
    @Transactional(readOnly = true)
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


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity getProductList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productReponseDtoList = products.stream().map(product -> new ProductResponse(product)).collect(Collectors.toList());
        return new ResponseEntity<>(productReponseDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getSellerList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> sellerProfiles = userRepository.findAllByRole(UserRoleEnum.SELLER, pageable);
        List<SellerProfileResponseDto> productReponseDtoList = sellerProfiles.getContent().stream().map(sellerProfile -> new SellerProfileResponseDto(sellerProfile)).collect(Collectors.toList());
        return new ResponseEntity<>(productReponseDtoList, HttpStatus.OK);
    }
     //TODO: 판매자의 정보를 저장하는 repository에 정보가 저장되어야함

    @Override
    @Transactional(readOnly = true)
    public SellerProfileResponseDto getSellerProfile(Long sellerId){
        SellerProfile sellerProfile = sellerRepository.findById(sellerId).orElseThrow(
                () -> new IllegalArgumentException("판매자 정보가 존재하지 않습니다.")
        );
        SellerProfileResponseDto sellerProfileResponseDto = new SellerProfileResponseDto(sellerProfile);
        return sellerProfileResponseDto;
    }

    @Override
    @Transactional
    public UserProfileResponseDto editUserProfile(UserProfileRequestDto userProfileRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("고객 정보가 존재하지 않습니다.")
        );
        user.update(userProfileRequestDto);
        userRepository.save(user);
        return new UserProfileResponseDto(user);
    }

    @Override
    @Transactional
    public void modifideroleCustomer(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(user.getRole() == CUSTOMER){
            Registration registration = registrationRepository.findByUserId(id).orElseThrow(
                    () -> new IllegalArgumentException("판매자 등록 요청이 존재하지 않습니다.")
            );
            user.changeSellerByCustomer(registration);
        }else {
            throw new IllegalArgumentException("현재 사용자는 Customer가 아닙니다.");
        }
    }
    @Override
    @Transactional
    public void modifideroleSeller(Long id) throws IllegalArgumentException {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        if(user.getRole() == SELLER){
            user.changeCustomerBySeller();
        }else {throw new IllegalArgumentException("현재 사용자는 Seller가 아닙니다.");}
    }
}