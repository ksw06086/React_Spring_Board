package com.swkim.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swkim.myboard.entity.LikyEntity;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, Integer>{

}
