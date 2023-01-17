package com.example.applefarm_.product.dto;

import com.example.applefarm_.product.entitiy.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProductResponse {
    private String productName;
    private int productPrice;
    //재고
    private int quantity;
    private String productImage;
    private String productDetail;
    private Long productCategory;

    public ProductResponse(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.quantity = product.getProductQuantity();
        this.productImage = product.getProductImage();
        this.productDetail = product.getProductDetail();
        this.productCategory = product.getProductCategory();
    }

    public static ProductResponse of(Product product) {
        return new ProductResponse(product);
    }

    public static List<ProductResponse> of(List<Product> products){
        return products.stream().map(ProductResponse::of).collect(Collectors.toList());
    }
}
