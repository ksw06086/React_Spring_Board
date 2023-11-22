package com.swkim.myboard.service.implement;

import com.swkim.myboard.dto.request.board.PostBoardRequestDto;
import com.swkim.myboard.dto.response.ResponseDto;
import com.swkim.myboard.dto.response.board.PostBoardResponseDto;
import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.ImageEntity;
import com.swkim.myboard.repository.BoardRepository;
import com.swkim.myboard.repository.ImageRepository;
import com.swkim.myboard.repository.UserRepository;
import com.swkim.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try {
            boolean existedEmail = userRepository.existsByEmail(email);
            if(!existedEmail) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);

            int boardNumber = boardRepository.save(boardEntity).getBoardNumber();
            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

}
