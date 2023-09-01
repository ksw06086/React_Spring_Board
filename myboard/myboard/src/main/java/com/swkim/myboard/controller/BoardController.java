package com.swkim.myboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.PopularSearchEntity;
import com.swkim.myboard.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired BoardService boardService;
	
	@GetMapping("/top3")
	public ResponseDto<List<BoardEntity>> getTop3() {
		return boardService.getTop3();
	}
	
	@GetMapping("/list")
	public ResponseDto<List<BoardEntity>> getList() {
		return boardService.getList();
	}
	
	@GetMapping("/popularsearchlist")
	public ResponseDto<List<PopularSearchEntity>> getPopularSearchList() {
		return boardService.getPopularSearchList();
	}
}
