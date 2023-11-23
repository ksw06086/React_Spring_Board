package com.swkim.myboard.controller;

import com.swkim.myboard.dto.request.board.PostBoardRequestDto;
import com.swkim.myboard.dto.response.board.GetBoardResponseDto;
import com.swkim.myboard.dto.response.board.PostBoardResponseDto;
import com.swkim.myboard.dto.response.board.PutFavoriteResponseDto;
import com.swkim.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
        return response;
    }

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String email
    ) {
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, email);
        return response;
    }

}
