package com.example.applefarm_.product.controller;

import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.product.dto.ProductResponse;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sellers/products")
public class ProductController {

    private final ProductService productService;

    //판매상품 등록
    @PostMapping("")
    public ResponseEntity saveProduct(@RequestBody ProductRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails){
        productService.saveProduct(request,userDetails.getUser());
        return ResponseEntity.ok("판매상품 등록 완료");
    }
    

    @GetMapping("/{pageChoice}")
    public List<ProductResponse> getProducts(@PathVariable int pageChoice, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return productService.getProducts(pageChoice,userDetails.getUser());
    }


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



}
