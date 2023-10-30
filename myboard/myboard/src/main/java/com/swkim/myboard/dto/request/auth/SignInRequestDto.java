package com.swkim.myboard.dto.request.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
