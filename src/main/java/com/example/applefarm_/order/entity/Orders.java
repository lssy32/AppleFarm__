package com.example.applefarm_.order.entity;

import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
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

    // enum 으로 바꿔야되는뎃..
    private OrderStatus orderStatus;

    public Orders(Long sellerId,Long productId, Long customerId, int quantity) {
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderStatus = OrderStatus.WAITING;
    }

    public void orderCompletionProcessing(){
        if(this.orderStatus == OrderStatus.COMPLETION) throw new CustomException(ExceptionStatus.Already_IS_COMPLETION);
        this.orderStatus = OrderStatus.COMPLETION;
    }

    public void validateSellerId(Long sellerId){
        if(!this.sellerId.equals(sellerId)) throw new IllegalArgumentException("판매자 불일치");
    }
}