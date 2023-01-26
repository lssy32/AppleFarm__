package com.example.applefarm_.product.dto;

import com.example.applefarm_.product.entitiy.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true,access = AccessLevel.PROTECTED)
public class ProductResponse {

    private final Long productId;
    private final String name;
    private final int productPrice;
    //재고
    private final int quantity;
    private final String productImage;
    private final String productDetail;
    private final Long productCategory;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.productPrice = product.getProductPrice();
        this.quantity = product.getProductQuantity();
        this.productImage = product.getProductImage();
        this.productDetail = product.getProductDetail();
        this.productCategory = product.getProductCategory();
    }



    }



