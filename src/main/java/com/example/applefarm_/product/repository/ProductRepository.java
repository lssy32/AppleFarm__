package com.example.applefarm_.product.repository;

import com.example.applefarm_.product.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByModifiedAtDesc();
    Optional<Product> findByIdAndUserId(Long id, Long userId);

}
