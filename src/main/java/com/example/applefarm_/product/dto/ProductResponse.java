package com.example.applefarm_.product.dto;

import com.example.applefarm_.product.entitiy.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public static ProductResponse of(Product product) {
        return new ProductResponse(product);
    }

    public static List<ProductResponse> of(List<Product> products){
        return products.stream().map(ProductResponse::of).collect(Collectors.toList());
    }

//    public static Page<ProductResponse> toDtoPage(Page<Product> productsPage) {
//        Page<ProductResponse> postResponseDtoPage = productsPage.map(m ->
//                ProductResponse.builder()
//                        .id(m.getId())
//                        .productName(m.getProductName())
//                        .productPrice(m.getProductPrice())
//                        .quantity(m.getProductQuantity())
//                        .productImage(m.getProductImage())
//                        .productDetail(m.getProductDetail())
//                        .productCategory(m.getProductCategory())
//                        .build());
//        return postResponseDtoPage;

    }



