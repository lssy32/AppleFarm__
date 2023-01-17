package com.example.applefarm_.customer.repository;

import com.example.applefarm_.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
