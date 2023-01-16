package com.example.applefarm_.product.repository;

import com.example.applefarm_.product.entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
