package com.example.applefarm_.customer.entity;

import com.example.applefarm_.customer.dto.CustomerProfileRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    private String image;

    public Customer(CustomerProfileRequestDto customerProfileRequestDto) {
        this.nickName = customerProfileRequestDto.getNickName();
        this.image = customerProfileRequestDto.getImage();
    }
}
