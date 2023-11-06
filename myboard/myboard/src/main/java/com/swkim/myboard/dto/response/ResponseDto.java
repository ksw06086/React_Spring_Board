package com.swkim.myboard.dto.response;

import com.swkim.myboard.common.ResponseCode;
import com.swkim.myboard.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//<DTO>
//- Controller에서 데이터를 View로 보낼 때 따로따로가 아니라 
//	객체 하나로 한번에 보낼 수 있음 
//- DTO 객체로 만들어 한번에 객체에 넣어 보내줌

@Data
@AllArgsConstructor
public class ResponseDto {
	private String code;
	private String message;

	public static ResponseEntity<ResponseDto> databaseError() {
		ResponseDto responsebody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responsebody);
	}

	public static ResponseEntity<ResponseDto> validationFailed() {
		ResponseDto responsebody = new ResponseDto(ResponseCode.VALIDATION_FAILED, ResponseMessage.VALIDATION_FAILED);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsebody);
	}
}
