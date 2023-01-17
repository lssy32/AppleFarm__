package com.example.applefarm_.user.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Seller extends User{

    private String sellerDetail;
    private String category;

    public Seller(Long id, String loginId, String loginPassword, UserRoleEnum role, String nickname, String image, String sellerDetail, String category) {
        super(id, loginId, loginPassword, role, nickname, image);
        this.sellerDetail = sellerDetail;
        this.category = category;
    }

}
