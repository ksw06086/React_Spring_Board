package com.swkim.myboard.dto;

import javax.validation.constraints.NotBlank;

import com.swkim.myboard.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {
	@NotBlank
	private String userEmail;
	@NotBlank
	private String userPassword;
}
