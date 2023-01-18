package com.example.applefarm_;

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

    @Override
    public void run(ApplicationArguments args){

        User admin = new User("admin", passwordEncoder.encode("admin"), "admin", "admin",UserRoleEnum.ADMIN);
        User seller = new User("seller", passwordEncoder.encode("seller"), "seller", "seller",UserRoleEnum.SELLER);
        User customer = new User("customer", passwordEncoder.encode("customer"), "customer", "customer",UserRoleEnum.CUSTOMER);
        User customer1 = new User("customer1", passwordEncoder.encode("customer"), "customer", "customer",UserRoleEnum.CUSTOMER);
        userRepository.save(admin);
        userRepository.save(seller);
        userRepository.save(customer);
        userRepository.save(customer1);

    }
}