package com.example.applefarm_.registration.repository;

import com.example.applefarm_.registration.entity.Registration;
import com.example.applefarm_.user.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    Optional<Registration> findByUserId(Long UserId);
    Optional<Registration> findById(Long id);
}