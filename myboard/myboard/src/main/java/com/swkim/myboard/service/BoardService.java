package com.swkim.myboard.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swkim.myboard.dto.ResponseDto;
import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.PopularSearchEntity;
import com.swkim.myboard.repository.BoardRepository;
import com.swkim.myboard.repository.PopularSearchRepository;

@Service
public class BoardService {
	
	@Autowired BoardRepository boardRepository;
	@Autowired PopularSearchRepository popularSearchRepository;
	
	public ResponseDto<List<BoardEntity>> getTop3() {
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
		
		try {
			boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.setFailed("DataBase Error!");
		}
		
		return ResponseDto.setSuccess("Success!", boardList);
	}
	
	public ResponseDto<List<BoardEntity>> getList() {
		
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		
		try {
			boardList = boardRepository.findByOrderByBoardWriteDateDesc();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.setFailed("Database Error!");
		}
		
		return ResponseDto.setSuccess("Success!", boardList);
	}
	
	public ResponseDto<List<PopularSearchEntity>> getPopularSearchList(){
		List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();
		
		try {
			popularSearchList = popularSearchRepository.findTop10ByOrderByPopularSearchCountDesc();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.setFailed("Database Error!");
		}
		
		return ResponseDto.setSuccess("Success!", popularSearchList);
	}
	
	public ResponseDto<List<BoardEntity>> getSearchList(String boardTitle){
		List<BoardEntity> boardList = new ArrayList<BoardEntity>();
		
		try {
			boardList = boardRepository.findByBoardTitleContains(boardTitle);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.setFailed("Database Error!");
		}
		
		return ResponseDto.setSuccess("Success!", boardList);
	}
}
