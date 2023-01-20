package com.example.applefarm_.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionStatus {

    Authenticated_EXCEPTION(401, " 인증 실패에 따른 예외가 발생했습니다. "),
    PASSWORDS_DO_NOT_MATCH(401,"비밀번호가 일치 하지 않습니다."),

    Authorized_EXCEPTION(403, " 인가에 따른 예외가 발생했습니다. "),
    NOT_SELLER(403,"현재 사용자는 Seller 가 아닙니다. "),
    NOT_CUSTOMER(403,"현재 사용자는 Customer 가 아닙니다. "),

    SELLER_INFORMATION_IS_EMPTY(404,"판매자 정보가 존재하지 않습니다."),
    REQURES_IS_EMPTY(404,"요청이 존재 하지 않습니다."),
    USER_IS_NOT_EXIST(404,"사용자가 존재 하지 않습니다."),
    Product_IS_NOT_EXIST(404, " 상품이 존재하지 않습니다. "),
    Order_IS_NOT_EXIST(404, " 주문내역이 존재하지 않습니다. "),
    Seller_IS_NOT_EXIST(404, "판매자가 존재하지 않습니다."),
    PAGINATION_IS_NOT_EXIST(404,"요청하신 페이지 내역이 존재하지 않습니다."),

    UserId_IS_EXIST(409, " 이미 등록된 아이디입니다. "),
    Points_IS_LACKING(409, "포인트가 부족합니다."),
    Already_IS_COMPLETION(409, "이미 수락처리된 오더입니다."),
    Already_IS_CANCEL(409,"이미 취소처리된 오더입니다."),
    Quantity_IS_LACKING(409, "재고가 부족합니다.");


    private final int statusCode;
    private final String message;

}
