package com.swkim.myboard.common;

public interface ResponseMessage {
    //* HTTP Status 200
    String SUCCESS = "Success.";    // 성공

    //* HTTP Status 400
    String VALIDATION_FAILED = "Validation failed.";    // 유효성 검증 실패
    String DUPLICATE_EMAIL = "Duplicate email.";    // 중복된 이메일
    String DUPLICATE_NICKNAME = "Duplicate nickname.";  // 중복된 닉네임
    String DUPLICATE_TEL_NUMBER = "Duplicate tel number.";  // 중복된 전화번호
    String NOT_EXISTED_USER = "This user dose not exist.";  // 존재하지 않는 유저
    String NOT_EXISTED_BOARD = "This board dose not exist.";    // 존재하지 않는 게시물

    //* HTTP Status 401
    String SIGN_IN_FAIL = "Login information mismatch.";    // 로그인 실패
    String AUTHORIZATION_FAIL = "authorization Failed.";    // 인증 실패

    //* HTTP Status 403
    String NO_PERMISSION = "Do not have permission.";   // 권한 없음

    //* HTTP Status 500
    String DATABASE_ERROR = "Database error.";      // 데이터베이스 에러
}
