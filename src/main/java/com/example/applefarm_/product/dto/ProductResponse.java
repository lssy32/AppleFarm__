package com.example.applefarm_.product.dto;

import com.example.applefarm_.product.entitiy.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponse {

    private Long productId;
    private String productName;
    private int productPrice;
    //재고
    private int quantity;
    private String productImage;
    private String productDetail;
    private Long productCategory;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.quantity = product.getProductQuantity();
        this.productImage = product.getProductImage();
        this.productDetail = product.getProductDetail();
        this.productCategory = product.getProductCategory();
    }



    }



