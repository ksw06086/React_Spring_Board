package com.swkim.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.dto.SignUpDto;
import com.swkim.myboard.entity.UserEntity;
import com.swkim.myboard.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired UserRepository userRepository;
	
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
		userRepository.save(userEntity);
		
		// 성공시 success response 반환!
		return ResponseDto.setSuccess("Sign Up Success!", null);
	}
}
