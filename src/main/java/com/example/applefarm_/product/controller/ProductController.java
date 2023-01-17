package com.example.applefarm_.product.controller;

import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.product.service.ProductService;
import com.example.applefarm_.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    //상품 CRUD ->> 판매자
    // 상품 조회 및 상품 구매 요청 -->고객
    private final ProductService productService;

    //판매상품 등록
    @PostMapping("")
    public ResponseEntity saveProduct(ProductRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.saveProduct(request,userDetails.getUser());
        return ResponseEntity.ok("판매상품 등록 완료");
    }

    //판매상품 조회, 내가 판매중인 상품 목록을 페이징하며 조회
    @GetMapping("")
    public ResponseEntity getProductList(){
        productService.getProductList();
        return ResponseEntity.ok(" 판매상품 조회 완료");
    }

    //판매상품 삭제 , 판매 상품 정보를 작성하여 목록에서 삭제

    //고객 요청 목록 조회 ? ,고객요청 목록 조회 : 모든상품의 고객요청 목록을 페이징하며 조회??

    //고객 요청 처리 ?

}
