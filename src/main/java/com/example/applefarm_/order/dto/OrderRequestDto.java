package com.example.applefarm_.order.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class OrderRequestDto {
    private final Long productId;
    private final int quantity;
    // 클래스 로더의 클래스 정보를 가지고 동작한다.
    // -> 불변객체를 어떻게 리플렉션이라는 애는 어떻게? 값을 넣어주는가
    // 빈 생성자(empty) default constructor ->

    @JsonCreator()
    @JsonProperty
}