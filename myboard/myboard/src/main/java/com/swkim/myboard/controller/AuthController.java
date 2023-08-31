package com.swkim.myboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.dto.SignUpDto;
import com.swkim.myboard.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired AuthService authService;
	
	@PostMapping("signup")
	public ResponseDto<?> signUp( @RequestBody SignUpDto requestBody ){
		ResponseDto<?> result = authService.signUp(requestBody);
		return result;
	}
}
