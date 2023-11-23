package com.swkim.myboard.service;

import com.swkim.myboard.dto.request.board.PostBoardRequestDto;
import com.swkim.myboard.dto.response.board.GetBoardResponseDto;
import com.swkim.myboard.dto.response.board.PostBoardResponseDto;
import com.swkim.myboard.dto.response.board.PutFavoriteResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
}
