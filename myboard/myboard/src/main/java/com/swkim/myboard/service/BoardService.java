package com.swkim.myboard.service;

import com.swkim.myboard.dto.request.board.PostBoardRequestDto;
import com.swkim.myboard.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
}
