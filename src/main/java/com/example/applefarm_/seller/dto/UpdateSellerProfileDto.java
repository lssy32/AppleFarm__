package com.example.applefarm_.seller.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class UpdateSellerProfileDto {
    @NotNull(message = "닉네임에는 공백이 들어갈 수 없습니다.")
    private final String sellerNickname;
    @NotNull(message = "이미지에는 공백이 들어갈 수 없습니다.")
    private final String sellerImage;
    @NotNull(message = "소개글에는 공백이 들어갈 수 없습니다.")
    private final String sellerDetails;
    @NotNull(message = "매칭주제정보에는 공백이 들어갈 수 없습니다.")
    private final Long sellerCategory;

}