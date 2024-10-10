package com.swkim.myboard.service;

import com.swkim.myboard.dto.request.user.PatchNicknameRequestDto;
import com.swkim.myboard.dto.request.user.PatchProfileImageRequestDto;
import com.swkim.myboard.dto.response.user.GetSignInUserResponseDto;
import com.swkim.myboard.dto.response.user.GetUserResponseDto;
import com.swkim.myboard.dto.response.user.PatchNicknameResponseDto;
import com.swkim.myboard.dto.response.user.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);

}
