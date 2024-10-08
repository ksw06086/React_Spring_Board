package com.swkim.myboard.dto.response.board;

import com.swkim.myboard.common.ResponseCode;
import com.swkim.myboard.common.ResponseMessage;
import com.swkim.myboard.dto.object.BoardListItem;
import com.swkim.myboard.dto.response.ResponseDto;
import com.swkim.myboard.entity.BoardListViewEntity;

import java.util.List;

public class GetTop3BoardListResponseDto extends ResponseDto {

    private List<BoardListItem> top3List;

    private GetTop3BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    }
}
