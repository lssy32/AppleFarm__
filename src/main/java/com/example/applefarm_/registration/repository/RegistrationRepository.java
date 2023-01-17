package com.example.applefarm_.registration.repository;

import com.example.applefarm_.registration.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}