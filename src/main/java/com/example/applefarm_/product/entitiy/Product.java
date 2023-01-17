package com.example.applefarm_.product.entitiy;

import com.example.applefarm_.product.dto.ProductRequest;
import com.example.applefarm_.user.entitiy.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "user_id") //판매자 ID (FK)
    @Column
     private Long userId;


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

    public Product(ProductRequest request, User user) {
        this.productName = request.getProductName();
        this.productPrice = request.getProductPrice();
        this.productQuantity = request.getQuantity();
        this.productImage = request.getProductImage();
        this.productDetail = request.getProductDetail();
        this.productCategory= request.getProductCategory();

    }

    public void updateProduct(ProductRequest request) {
        this.productName = request.getProductName();
        this.productPrice = request.getProductPrice();
        this.productQuantity = request.getQuantity();
        this.productImage = request.getProductImage();
        this.productDetail = request.getProductDetail();
        this.productCategory= request.getProductCategory();
    }
}
