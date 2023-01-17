package com.example.applefarm_.user.entitiy;

import com.example.applefarm_.security.config.Timestamp;
import lombok.Getter;
import com.example.applefarm_.user.dto.UserProfileRequestDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User extends Timestamp {

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
    private String sellerNickname;
    private String sellerImage;
    private String sellerDetails;
    private String sellerCategory;

    public User(String loginId, String loginPassword, String nickName, String image, UserRoleEnum role) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.nickName = nickName;
        this.image = image;
        this.role = role;
    }

    public void changeSellerByCustomer(){  // 커스터머 > 셀러
        this.role = UserRoleEnum.SELLER;
    }
    public void changeCustomerBySeller(){ // 셀러 > 커스터머
        this.role = UserRoleEnum.CUSTOMER;
    }



    public void update(UserProfileRequestDto userProfileRequestDto) {
        this.nickName = userProfileRequestDto.getNickname();
        this.image = userProfileRequestDto.getImage();
    }
}
