package com.example.applefarm_.product.controller;

import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.service.ProductService;
import com.example.applefarm_.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    //상품 CRUD ->> 판매자
    // 상품 조회 및 상품 구매 요청 -->고객
    private final ProductService productService;

    //판매상품 등록
    @PostMapping("")
    public ResponseEntity saveProduct(@RequestBody ProductRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.saveProduct(request,userDetails.getUser());
        return ResponseEntity.ok("판매상품 등록 완료");
    }


    //판매상품 조회 Todo 페이징처리
//    @GetMapping("")
//    public Page<Product> getProductList(@PageableDefault(size = 3, sort ="id",direction = Sort.Direction.DESC)Pageable pageable){
//        return productService.getProductList(pageable);
//
//    }
    //판매상품 수정
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody ProductRequest request){
        productService.updateProduct(id,userDetails.getUser(),request);
        return ResponseEntity.ok("판매상품 수정 완료");
    }




    //판매상품 삭제 , 판매 상품 정보를 작성하여 목록에서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.deleteProduct(id,userDetails.getUser());
        return ResponseEntity.ok("판매 상품 삭제 완료");
    }

    //고객 요청 목록 조회 ? ,고객요청 목록 조회 : 모든상품의 고객요청 목록을 페이징하며 조회??
    //고객 요청 처리 ?

}
