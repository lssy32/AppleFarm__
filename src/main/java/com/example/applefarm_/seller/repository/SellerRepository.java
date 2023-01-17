package com.example.applefarm_.seller.repository;

import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.seller.entitiy.SellerProfile;
import com.example.applefarm_.user.entitiy.Seller;
import com.example.applefarm_.user.entitiy.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerProfile, Long> {
    Page<SellerProfile>findAll(Pageable pageable);
    Optional<SellerProfile> findById(Long sellerId);
}
