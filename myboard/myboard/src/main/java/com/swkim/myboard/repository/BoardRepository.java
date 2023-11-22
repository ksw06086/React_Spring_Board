package com.swkim.myboard.repository;

import com.swkim.myboard.entity.BoardEntity;
import com.swkim.myboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {



}
