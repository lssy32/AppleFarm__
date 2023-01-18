package com.example.applefarm_.product.service;

import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.product.dto.ProductResponse;
import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.user.entitiy.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse saveProduct(ProductRequest request, User user) {
        Product product = productRepository.save(new Product(request,user));
        return new ProductResponse(product);
    }


    @Transactional
    public List<ProductResponse> getProducts(int pageChoice, User user) {
        Page<Product> products = productRepository.findAllByUserId(user.getId(),pageableSetting(pageChoice));
        List<ProductResponse> productResponseList = products.stream().map(ProductResponse::new).collect(Collectors.toList());
        return productResponseList;
    }

    private Pageable pageableSetting(int pageChoice) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        Pageable pageable = PageRequest.of(pageChoice-1,4,sort);
        return pageable;
    }

    @Transactional
    public void updateProduct(Long id, User user,ProductRequest productRequest) {
        Product foundProduct = productRepository.findByIdAndUserId(id,user.getId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 판매상품이 존재하지 않습니다.")
        );
        foundProduct.updateProduct(productRequest);
    }

    @Transactional
    public void deleteProduct(Long id, User user) {
        Product foundProduct = productRepository.findByIdAndUserId(id,user.getId()).orElseThrow(
                ()-> new IllegalArgumentException("해당 판매상품이 존재하지 않습니다.")
        );
        productRepository.deleteById(id);
    }


}
