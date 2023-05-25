package com.example.demo.hashtag.controller;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.hashtag.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hashtag")
@RequiredArgsConstructor
public class HashTagController {

    private final HashtagService hashtagService;

    @GetMapping
    public ResponseEntity<List<HashtagResponse>> getAllHashtags() {
        return ResponseEntity.ok(hashtagService.getAllHashtags());
    }
}
