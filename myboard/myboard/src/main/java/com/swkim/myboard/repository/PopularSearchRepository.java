package com.swkim.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swkim.myboard.entity.PopularSearchEntity;

@Repository
public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, String>{

}
