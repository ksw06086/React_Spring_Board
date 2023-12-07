package com.swkim.myboard.repository;

import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.FavoriteEntity;
import com.swkim.myboard.entity.ImageEntity;
import com.swkim.myboard.repository.resultSet.GetFavoriteListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber, String userEmail);

    @Query(
        value =
        "SELECT " +
        "    U.email AS email, " +
        "    U.nickname AS nickname, " +
        "    U.profile_image AS profileImage " +
        "FROM favorite AS F " +
        "INNER JOIN user AS U " +
        "ON F.user_email = U.email " +
        "WHERE F.board_number = ?1",
        nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);
}
