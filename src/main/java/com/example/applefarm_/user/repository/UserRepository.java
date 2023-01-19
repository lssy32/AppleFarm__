package com.example.applefarm_.user.repository;


import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLoginId(String loginId);
    Page<User> findAllByRole(UserRoleEnum userRoleEnum, Pageable pageable);
    User findBySellerNickname(String nickname);
}
