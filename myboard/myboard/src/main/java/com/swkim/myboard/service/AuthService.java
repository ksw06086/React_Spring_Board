package com.swkim.myboard.service;

import com.swkim.myboard.dto.request.auth.SignInRequestDto;
import com.swkim.myboard.dto.request.auth.SignUpRequestDto;
import com.swkim.myboard.dto.response.auth.SignInResponseDto;
import com.swkim.myboard.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
