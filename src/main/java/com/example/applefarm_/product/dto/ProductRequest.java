package com.example.applefarm_.product.dto;


import lombok.Getter;

@Getter
public class ProductRequest {
    private String name;
    private int productPrice;
    //재고
    private int quantity;
    private String productImage;
    private String productDetail;
    private Long productCategory;

}
