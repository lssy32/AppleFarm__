package com.example.applefarm_.product.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true,access = AccessLevel.PROTECTED)
public class ProductRequest {
    private final String name;
    private final int productPrice;
    //재고
    private final int quantity;
    private final String productImage;
    private final String productDetail;
    private final Long productCategory;

}
