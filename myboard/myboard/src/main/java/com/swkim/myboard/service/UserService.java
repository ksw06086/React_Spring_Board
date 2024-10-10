package com.swkim.myboard.service;

import com.swkim.myboard.dto.response.user.GetSignInUserResponseDto;
import com.swkim.myboard.dto.response.user.GetUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

}
