package com.swkim.myboard.controller;

import com.swkim.myboard.dto.request.auth.SignInRequestDto;
import com.swkim.myboard.dto.request.auth.SignUpRequestDto;
import com.swkim.myboard.dto.response.auth.SignInResponseDto;
import com.swkim.myboard.dto.response.auth.SignUpResponseDto;
import com.swkim.myboard.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        return authService.signUp(requestBody);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
            ) {
        return authService.signIn(requestBody);
    }
}

