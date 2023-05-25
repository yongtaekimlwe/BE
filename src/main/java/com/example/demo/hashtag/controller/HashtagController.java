package com.example.demo.hashtag.controller;

import com.example.demo.hashtag.dto.HashtagsResponse;
import com.example.demo.hashtag.service.HashtagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("/list")
    public ResponseEntity<HashtagsResponse> findHashtags() {
        HashtagsResponse hashtagsResponse = hashtagService.findHashtags();
        return ResponseEntity.ok(hashtagsResponse);
    }
}