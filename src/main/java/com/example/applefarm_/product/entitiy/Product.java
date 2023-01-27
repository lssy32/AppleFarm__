package com.example.applefarm_.product.entitiy;

import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
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
   @Column(nullable = false, name = "seller_id")
   private Long sellerId;


    @Column(nullable = false, name = "product_name")
    private String name;

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

    public Product(Long sellerId, String name, int productPrice, int productQuantity, String productImage, String productDetail, Long productCategory) {
        this.sellerId = sellerId;
        this.name = name;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.productDetail = productDetail;
        this.productCategory = productCategory;
    }

    public Product(ProductRequest request, Long sellerId) {
        this.sellerId = sellerId;
        this.name = request.getName();
        this.productPrice = request.getProductPrice();
        this.productQuantity = request.getQuantity();
        this.productImage = request.getProductImage();
        this.productDetail = request.getProductDetail();
        this.productCategory= request.getProductCategory();

    }

    public void updateProduct(ProductRequest request) {
        this.name = request.getName();
        this.productPrice = request.getProductPrice();
        this.productQuantity = request.getQuantity();
        this.productImage = request.getProductImage();
        this.productDetail = request.getProductDetail();
        this.productCategory= request.getProductCategory();
    }

    public void subtractQuantity(int quantity) {
        this.productQuantity -= quantity;
        if(this.productQuantity < 0) throw new CustomException(ExceptionStatus.Quantity_IS_LACKING);
    }

    public void putQuantityBack(int quantity) {
        this.productQuantity += quantity;
    }
}
