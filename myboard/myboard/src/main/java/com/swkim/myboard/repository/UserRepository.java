package com.swkim.myboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swkim.myboard.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

}
