package com.example.demo.picture.repository;

import com.example.demo.picture.domain.PictureBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PictureBoardRepository extends JpaRepository<PictureBoard, Integer> {
    @Query("SELECT ib FROM image_board ib JOIN FETCH ib.hashtags WHERE ib.imageId = :imageId")
    Optional<PictureBoard> findByIdWithHashTags(@Param("imageId") int imageId);
}
