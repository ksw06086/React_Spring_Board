package com.swkim.myboard.service;

import com.swkim.myboard.dto.response.search.GetPopularListResponseDto;
import com.swkim.myboard.dto.response.search.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    ResponseEntity<? super GetPopularListResponseDto> getPopularList();

    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
}
