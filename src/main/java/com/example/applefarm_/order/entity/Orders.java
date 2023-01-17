package com.example.applefarm_.order.entity;

import com.example.applefarm_.product.entitiy.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private Long customerId;
    private Long productId;
    private int quantity;

    //논리삭제를 위한 필드
    private int isDeleted;

    public Orders(Product product, Long customerId, int quantity) {
        this.sellerId = product.getId();
        this.customerId = customerId;
        this.productId = product.getId();
        this.quantity = quantity;
        this.isDeleted = 0;
    }

    public void orderCompletionProcessing(){
        this.isDeleted = 1;
    }

    public void validateSellerId(Long sellerId){
        if(!this.sellerId.equals(sellerId)) throw new IllegalArgumentException("판매자 불일치");
    }
}