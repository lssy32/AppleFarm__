package com.example.applefarm_.product.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    // userId -> FK
    @Column(nullable = false, name = "product_name")
    private String productName;
    @Column(nullable = false, name = "product_price")
    private int productPrice;
    @Column(nullable = false, name = "product_quantity")
    private int productQuantity;
    @Column(nullable = false, name = "product_image")
    private String productImage;
    @Column(nullable = false, name = "product_detail")
    private String productDetail;
    @Column(nullable = false, name = "product_category")
    private Long productCategory;

}
