package com.swkim.myboard.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.entity.BoardEntity;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@GetMapping("/top3")
	public ResponseDto<List<BoardEntity>> getTop3() {
		return null;
	}
	
	@GetMapping("/list")
	public ResponseDto<List<BoardEntity>> getList() {
		return null;
	}
}
