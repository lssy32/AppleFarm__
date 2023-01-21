package com.example.applefarm_.product.dto;


import com.example.applefarm_.product.entitiy.Product;
import lombok.Getter;

@Getter
public class ProductRequest {
    private String productName;
    private int productPrice;
    //재고
    private int quantity;
    private String productImage;
    private String productDetail;
    private Long productCategory;

    public Product toEntity(Long sellerId){
        Product product = new Product(
        );
        .sellerId(sellerId)
        // 그래서 여기서 빌더를 쓴다.
    }
    //TODO : Ref builder 참고용
//    public Member toEntity(String password){
//        return Member.builder()
//                .username(this.getUsername())
//                .name(this.getName())
//                .nickName(this.getNickName())
//                .password(password)
//                .build();
//    }

}
