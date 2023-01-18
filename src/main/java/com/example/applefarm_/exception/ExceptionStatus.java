package com.example.applefarm_.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionStatus {

    UserId_IS_EXIST(409, " 이미 등록된 아이디입니다. "),
    Product_IS_NOT_EXIST(404, " 상품이 존재하지 않습니다. "),
    Order_IS_NOT_EXIST(404, " 주문내역이 존재하지 않습니다. "),
    Seller_IS_NOT_EXIST(404, "판매자가 존재하지 않습니다."),
    PAGINATION_IS_NOT_EXIST(404,"요청하신 페이지 내역이 존재하지 않습니다."),

    Authenticated_EXCEPTION(401, " 인증 실패에 따른 예외가 발생했습니다. "),
    Authorized_EXCEPTION(403, " 인가에 따른 예외가 발생했습니다. ");

    private final int statusCode;
    private final String message;

}
