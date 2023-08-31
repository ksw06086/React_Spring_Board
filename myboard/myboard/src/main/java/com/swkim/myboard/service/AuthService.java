package com.swkim.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.dto.SignInDto;
import com.swkim.myboard.dto.SignInResponseDto;
import com.swkim.myboard.dto.SignUpDto;
import com.swkim.myboard.entity.UserEntity;
import com.swkim.myboard.repository.UserRepository;
import com.swkim.myboard.security.TokenProvider;

@Service
public class AuthService {
	
	@Autowired UserRepository userRepository;
	
	@Autowired TokenProvider tokenProvider;
	
	// 회원가입
	public ResponseDto<?> signUp(SignUpDto dto){
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		String userPasswordCheck = dto.getUserPasswordCheck();
		
		// email 중복 확인
		try {
			if(userRepository.existsById(userEmail))
				return ResponseDto.setFailed("Existed Email!");	
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}
		
		// 비밀번호가 서로 다르면 Failed response 반환!
		try {
			if(!userPassword.equals(userPasswordCheck))
				return ResponseDto.setFailed("Password does not matched!");
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}
		
		// UserEntity 생성
		UserEntity userEntity = new UserEntity(dto);
		
		// UserRepository를 이용해서 데이터베이스에 Entity 저장!!
		try {
			userRepository.save(userEntity);
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}
		
		// 성공시 success response 반환!
		return ResponseDto.setSuccess("Sign Up Success!", null);
	}
	
	// 로그인
	public ResponseDto<SignInResponseDto> signIn(SignInDto dto){
		String userEmail = dto.getUserEmail();
		String userPassword = dto.getUserPassword();
		
		try {
			boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
			if(!existed) return ResponseDto.setFailed("Sign In Information Does Not Match!");
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}
		
		UserEntity userEntity = null;
		try {
			userEntity = userRepository.findById(userEmail).get();
		} catch (Exception e) {
			return ResponseDto.setFailed("Data Base Error!");
		}
		userEntity.setUserPassword("");
		
		String token = tokenProvider.create(userEmail);
		int exprTime = 3600000;
		
		SignInResponseDto signInResponseDto = new SignInResponseDto(token, exprTime, userEntity); 
		return ResponseDto.setSuccess("Sign In Success", signInResponseDto);
	}
}
