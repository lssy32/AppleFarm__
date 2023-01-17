package com.example.applefarm_.user.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String loginPassword;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String loginId, String loginPassword, UserRoleEnum role) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.role = role;
    }

    public void changeSellerByCustomer(){  // 커스터머 > 셀러
        this.role = UserRoleEnum.SELLER;
    }
    public void changeCustomerBySeller(){ // 셀러 > 커스터머
        this.role = UserRoleEnum.CUSTOMER;
    }


}
