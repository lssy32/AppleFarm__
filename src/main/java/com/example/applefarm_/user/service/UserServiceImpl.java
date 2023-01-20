package com.example.applefarm_.user.service;


import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
import com.example.applefarm_.product.dto.ProductResponse;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.security.jwt.JwtUtil;
import com.example.applefarm_.seller.dto.SellerProfileResponseDto;
import com.example.applefarm_.user.dto.LoginRequestDto;
import com.example.applefarm_.user.dto.SignupRequestDto;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import com.example.applefarm_.user.dto.UserProfileResponseDto;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import com.example.applefarm_.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.applefarm_.user.entitiy.UserRoleEnum.SELLER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) throws IllegalArgumentException {
        String loginId = signupRequestDto.getLoginId();
        String loginPassword = passwordEncoder.encode(signupRequestDto.getLoginPassword());
        // 회원 중복 확인
        if (userRepository.findByLoginId(loginId).isPresent()) {
            throw new CustomException(ExceptionStatus.UserId_IS_EXIST);
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
                () -> new CustomException(ExceptionStatus.USER_IS_NOT_EXIST)
        );
        if (!passwordEncoder.matches(loginRequestDto.getLoginPassword(), user.getLoginPassword())) {
            throw new CustomException(ExceptionStatus.PASSWORDS_DO_NOT_MATCH);
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getLoginId(), user.getRole()));
    }


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity getProductList(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
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

    @Override
    @Transactional(readOnly = true)
    public SellerProfileResponseDto getSellerProfile(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionStatus.SELLER_INFORMATION_IS_EMPTY)
        );
        if(!user.isVaildateRole(SELLER)){
            throw new CustomException(ExceptionStatus.NOT_SELLER);
        }
        return new SellerProfileResponseDto(user);
    }

    @Override
    @Transactional
    public UserProfileResponseDto editUserProfile(UserProfileRequestDto userProfileRequestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new CustomException(ExceptionStatus.USER_IS_NOT_EXIST)
        );
        user.updateUserProfile(userProfileRequestDto);
        userRepository.save(user);
        return new UserProfileResponseDto(user);
    }
    @Override
    @Transactional
    public void signout(HttpServletRequest request) {
        Claims claims = jwtUtil.getUserInfoFromToken(jwtUtil.resolveToken(request)).setExpiration(new Date());
        jwtUtil.deleteAuthentication(claims.getSubject());
    }

    @Transactional
    public List<ProductResponse> getProductsByKeyword(String keyword, int page) {
        Page<Product> products = productRepository.findAllByProductNameContaining(keyword, pageableSetting(page));
        List<ProductResponse> productResponseList = products.stream().map(ProductResponse::new).collect(Collectors.toList());
        return productResponseList;
    }

    @Transactional
    public List<ProductResponse> getProductsByNickname(String nickname, int page) {
        Long id = userRepository.findBySellerNickname(nickname).getId();
        Page<Product> products = productRepository.findAllBySellerId(id, pageableSetting(page));
        List<ProductResponse> productResponseList = products.stream().map(ProductResponse::new).collect(Collectors.toList());
        return productResponseList;
    }

    private Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        Pageable pageable = PageRequest.of(pageChoice-1,4,sort)   ;
        return pageable;
    }
}