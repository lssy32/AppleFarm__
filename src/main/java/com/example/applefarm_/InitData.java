package com.example.applefarm_;

import com.example.applefarm_.product.entitiy.Product;
import com.example.applefarm_.product.repository.ProductRepository;
import com.example.applefarm_.user.entitiy.User;
import com.example.applefarm_.user.entitiy.UserRoleEnum;
import com.example.applefarm_.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InitData implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args){

        User admin = new User("admin", passwordEncoder.encode("admin"), "admin","admin",UserRoleEnum.ADMIN);
        User seller = new User("seller", passwordEncoder.encode("seller"), "seller", "seller",UserRoleEnum.SELLER, "sellerNickname");
        User seller1 = new User("seller1", passwordEncoder.encode("seller"), "seller", "seller",UserRoleEnum.SELLER, "sellerNickname1");
        User customer = new User("customer", passwordEncoder.encode("customer"), "customer", "customer",UserRoleEnum.CUSTOMER);
        User customer1 = new User("customer1", passwordEncoder.encode("customer"), "customer", "customer",UserRoleEnum.CUSTOMER);
        userRepository.save(admin);
        userRepository.save(seller);
        userRepository.save(seller1);
        userRepository.save(customer);
        userRepository.save(customer1);
        Product product = new Product(3L, "macbook", 20000, 50, "www.naver.com", "Macbook is good", 1L);
        Product product1 = new Product(2L, "mac", 30000, 50, "www.google.com", "Mac is good", 1L);
        Product product2 = new Product(2L, "iPhone", 40000, 50, "www.daum.net", "iPhone is good", 2L);
        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);

    }
}