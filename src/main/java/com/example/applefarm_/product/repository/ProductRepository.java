package com.example.applefarm_.product.repository;

import com.example.applefarm_.product.entitiy.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);
    Optional<Product> findByIdAndSellerId(Long id, Long userId);

    Page<Product> findAllBySellerId(Long id, Pageable pageableSetting);
    Page<Product> findAllByNameContaining(String productName, Pageable pageableSetting);

    void deleteAllBySellerId(Long sellerId);

}