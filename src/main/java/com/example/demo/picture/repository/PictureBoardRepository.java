package com.example.demo.picture.repository;

import com.example.demo.picture.domain.PictureBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureBoardRepository extends JpaRepository<PictureBoard, Integer> { }
