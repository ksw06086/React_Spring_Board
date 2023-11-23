package com.swkim.myboard.repository;

import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.FavoriteEntity;
import com.swkim.myboard.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber, String userEmail);

}
