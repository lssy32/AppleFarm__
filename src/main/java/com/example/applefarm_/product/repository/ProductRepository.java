package com.example.applefarm_.product.repository;

import com.example.applefarm_.product.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByModifiedAtDesc();

}
