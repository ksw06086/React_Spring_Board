package com.swkim.myboard.service.implement;

import com.swkim.myboard.dto.response.ResponseDto;
import com.swkim.myboard.dto.response.search.GetPopularListResponseDto;
import com.swkim.myboard.dto.response.search.GetRelationListResponseDto;
import com.swkim.myboard.repository.SearchLogRepository;
import com.swkim.myboard.repository.resultSet.GetPopularListResultSet;
import com.swkim.myboard.repository.resultSet.GetRelationListResultSet;
import com.swkim.myboard.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getPopularList();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

        List<GetRelationListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getRelationList(searchWord);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRelationListResponseDto.success(resultSets);
    }
}
