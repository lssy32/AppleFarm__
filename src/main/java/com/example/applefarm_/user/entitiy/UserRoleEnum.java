package com.example.applefarm_.user.entitiy;

public enum UserRoleEnum {
    CUSTOMER(Authority.CUSTOMER),  // 사용자 권한
    SELLER(Authority.SELLER), // 매니저 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한
    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority(){
        return authority;
    }

    public static class Authority{
        public static final String CUSTOMER = "ROLE_CUSTOMER";
        public static final String SELLER = "ROLE_SELLER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
