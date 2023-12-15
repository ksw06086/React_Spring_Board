package com.swkim.myboard.repository;

import com.swkim.myboard.entity.CommentEntity;
import com.swkim.myboard.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {


}
