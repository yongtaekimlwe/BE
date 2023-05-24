package com.example.demo.picture.repository;

import com.example.demo.picture.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Integer> { }
