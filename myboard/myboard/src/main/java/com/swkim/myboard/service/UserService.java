package com.swkim.myboard.service;

import com.swkim.myboard.dto.response.user.GetSignInUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);

}
