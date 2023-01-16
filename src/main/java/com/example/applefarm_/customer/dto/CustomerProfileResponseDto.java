package com.example.applefarm_.customer.dto;

import com.example.applefarm_.customer.entity.Customer;

public class CustomerProfileResponseDto {


    private String nickName;
    private String image;

    public CustomerProfileResponseDto(Customer customer) {
        this.nickName = customer.getNickName();
        this.image = customer.getImage();

    }
}
