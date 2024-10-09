package com.swkim.myboard.repository;

import com.swkim.myboard.entity.ImageEntity;
import com.swkim.myboard.entity.SearchLogEntity;
import com.swkim.myboard.repository.resultSet.GetPopularListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {

    @Query(
        value=
            "SELECT " +
            "search_word AS searchWord, count(search_word) AS count " +
            "FROM search_log " +
            "WHERE relation IS FALSE " +
            "GROUP BY search_word " +
            "ORDER BY count DESC " +
            "LIMIT 15 ",
        nativeQuery = true
    )
    List<GetPopularListResultSet> getPopularList();

}
