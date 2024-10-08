package com.swkim.myboard.dto.response.board;

import com.swkim.myboard.common.ResponseCode;
import com.swkim.myboard.common.ResponseMessage;
import com.swkim.myboard.dto.object.BoardListItem;
import com.swkim.myboard.dto.response.ResponseDto;
import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetLatestBoardListResponseDto extends ResponseDto {

    private List<BoardListItem> latestList;

    private GetLatestBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.latestList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListViewEntity> boardEntities) {
        GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto((boardEntities));
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
