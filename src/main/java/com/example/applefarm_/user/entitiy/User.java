package com.example.applefarm_.user.entitiy;

import com.example.applefarm_.security.config.Timestamp;
import lombok.Getter;
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
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String loginId, String loginPassword, UserRoleEnum role) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.role = role;
    }

}
