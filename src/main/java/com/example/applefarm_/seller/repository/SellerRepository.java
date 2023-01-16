package com.example.applefarm_.seller.repository;

import com.example.applefarm_.seller.entitiy.SellerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<SellerProfile, Long> {
}
