package com.example.demo.hashtag.service;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.hashtag.domain.Hashtag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HashtagService {
    public List<HashtagResponse> getHashtagResponses(List<Hashtag> hashtags) {
        return hashtags.stream()
                .map(hashtag -> new HashtagResponse(hashtag.getTagName(), hashtag.getTagIcon()))
                .collect(Collectors.toList());
    }
}
