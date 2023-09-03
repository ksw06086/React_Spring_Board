package com.swkim.myboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swkim.myboard.dto.PatchUserDto;
import com.swkim.myboard.dto.PatchUserResponseDto;
import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@PatchMapping("/")
	public ResponseDto<PatchUserResponseDto> patchUser(
			@RequestBody PatchUserDto requestBody,
			@AuthenticationPrincipal String userEmail) {
		
		return userService.patchUser(requestBody, userEmail);
	}
}
