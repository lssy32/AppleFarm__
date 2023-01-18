package com.example.applefarm_.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionStatus {


    PASSWORDS_DO_NOT_MATCH(403,"비밀번호가 일치 하지 않습니다."),
    SELLER_INFORMATION_IS_EMPTY(404,"판매자 정보가 존재하지 않습니다."),
    REQURES_IS_EMPTY(404,"요청이 존재 하지 않습니다."),
    DOESN_NOT_USER(403,"사용자가 존재 하지 않습니다."),
    NOT_SELLER(403,"현재 사용자는 Seller 가 아닙니다. "),
    NOT_CUSTOMER(403,"현재 사용자는 Customer 가 아닙니다. "),
    UserId_IS_EXIST(409, " 이미 등록된 아이디입니다. "),
    Already_IS_DELETED(409, "이미 삭제 된 오더입니다."),
    Product_IS_NOT_EXIST(404, " 상품이 존재하지 않습니다. "),
    Order_IS_NOT_EXIST(404, " 주문내역이 존재하지 않습니다. "),
    Seller_IS_NOT_EXIST(404, "판매자가 존재하지 않습니다."),
    PAGINATION_IS_NOT_EXIST(404,"요청하신 페이지 내역이 존재하지 않습니다."),

    Authenticated_EXCEPTION(401, " 인증 실패에 따른 예외가 발생했습니다. "),
    Authorized_EXCEPTION(403, " 인가에 따른 예외가 발생했습니다. ");

    private final int statusCode;
    private final String message;

}
