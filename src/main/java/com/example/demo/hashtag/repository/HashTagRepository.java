package com.example.demo.hashtag.repository;

import com.example.demo.picture.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<Hashtag, Integer> { }
