package com.example.applefarm_.order.entity;

import com.example.applefarm_.exception.CustomException;
import com.example.applefarm_.exception.ExceptionStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Enumerated(value = EnumType.STRING)
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
        if(this.orderStatus == OrderStatus.CANCELING) throw new CustomException(ExceptionStatus.Already_IS_CANCEL);
        this.orderStatus = OrderStatus.COMPLETION;
    }

    public void orderCancelingProcessing() {
        if(this.orderStatus == OrderStatus.CANCELING) throw new CustomException(ExceptionStatus.Already_IS_CANCEL);
        if(this.orderStatus == OrderStatus.COMPLETION) throw new CustomException(ExceptionStatus.Already_IS_COMPLETION);

        this.orderStatus = OrderStatus.CANCELING;
    }

    public void validateSellerId(Long sellerId){
        if(!this.sellerId.equals(sellerId)) throw new IllegalArgumentException("판매자 불일치");
    }

}