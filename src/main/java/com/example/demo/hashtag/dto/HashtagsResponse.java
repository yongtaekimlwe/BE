package com.example.demo.hashtag.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class HashtagsResponse {
    List<HashtagResponse> hashtags;

    public HashtagsResponse(List<HashtagResponse> hashtags) {
        this.hashtags = hashtags;
    }
}
