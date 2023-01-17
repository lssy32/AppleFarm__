package com.example.applefarm_.user.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class Seller extends User{


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
