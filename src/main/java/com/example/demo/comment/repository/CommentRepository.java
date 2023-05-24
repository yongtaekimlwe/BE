package com.example.demo.comment.repository;

import com.example.demo.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT c FROM image_board_comment c WHERE c.pictureBoard.imageId = :imageId")
    List<Comment> findCommentsByImageId(@Param("imageId") int imageId);
}
