package com.swkim.myboard.repository;

import com.swkim.myboard.entity.CommentEntity;
import com.swkim.myboard.entity.ImageEntity;
import com.swkim.myboard.repository.resultSet.GetCommentListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    @Query(
        value= """
        SELECT
            U.nickname AS nickname,
            U.profileImage AS profileImage,
            C.writeDatetime AS writeDatetime,
            C.content AS content
        FROM comment AS C
        INNER JOIN user AS U
        ON C.userEmail = U.email
        WHERE C.boardNumber = :boardNumber
        ORDER BY writeDatetime DESC
        """
    )
    List<GetCommentListResultSet> getCommentList(Integer boardNumber);

    @Transactional
    void deleteByBoardNumber(Integer boardNumber);

}
