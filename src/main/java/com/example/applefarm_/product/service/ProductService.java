package com.example.applefarm_.product.service;

import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.product.dto.ProductResponse;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.user.entitiy.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse saveProduct(ProductRequest request, User user) {
        Product product = productRepository.save(new Product(request,user));
        return new ProductResponse(product);
    }
    //Todo 페이징처리
    @Transactional
    public List<ProductResponse> getProductList() {
        return ProductResponse.of(productRepository.findAllByOrderByModifiedAtDesc());


    }
}
