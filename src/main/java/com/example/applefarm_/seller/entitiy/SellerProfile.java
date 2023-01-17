package com.example.applefarm_.seller.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class SellerProfile {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false, name = "seller_nickname", unique = true)
    private String sellerNickname;
    @Column(nullable = false, name = "seller_image")
    private String sellerImage;
    @Column(nullable = false, name = "seller_detail")
    private String sellerDetail;
    @Column(nullable = false, name = "category")
    private Long category;
}
