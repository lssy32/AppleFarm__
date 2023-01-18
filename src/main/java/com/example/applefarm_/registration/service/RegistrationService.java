package com.example.applefarm_.registration.service;

import com.example.applefarm_.registration.dto.RegistrationRequestDto;
import com.example.applefarm_.registration.entity.Registration;
import com.example.applefarm_.registration.repository.RegistrationRepository;
import com.example.applefarm_.user.dto.SellerRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    @Transactional
    public void sellerRegist(RegistrationRequestDto registrationRequestDto, Long id) {
        if(registrationRepository.findByUserId(id).isPresent()){
            throw new IllegalArgumentException("이미 신청하셨습니다.");
        }
        Registration registration = new Registration(registrationRequestDto, id);
        registrationRepository.save(registration);
    }
}
