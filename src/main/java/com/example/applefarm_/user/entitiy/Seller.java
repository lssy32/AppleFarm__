package com.example.applefarm_.user.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class Seller extends User{

    private String sellerDetail;
    private String category;

    public Seller(Long id, String loginId, String loginPassword, UserRoleEnum role, String nickname, String image, String sellerDetail, String category) {
        super(id, loginId, loginPassword, role, nickname, image);
        this.sellerDetail = sellerDetail;
        this.category = category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String image;

    public Seller(Long id, String nickName, String image) {
        this.id = id;
        this.nickName = nickName;
        this.image = image;
    }

    public Seller(String loginId, String loginPassword, UserRoleEnum role, Long id, String nickName, String image) {
        super(loginId, loginPassword, role);
        this.id = id;
        this.nickName = nickName;
        this.image = image;
    }

}
