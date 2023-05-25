package com.example.demo.hashtag.service;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.hashtag.dto.HashtagsResponse;
import com.example.demo.hashtag.repository.HashtagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public List<HashtagResponse> getHashtagResponses(List<Hashtag> hashtags) {
        return hashtags.stream()
                .map(hashtag -> new HashtagResponse(hashtag.getTagId() ,hashtag.getTagName(), hashtag.getTagIcon()))
                .collect(Collectors.toList());
    }

    public HashtagsResponse findHashtags() {
        List<HashtagResponse> hashtagResponses = hashtagRepository.findAll()
                .stream()
                .map(HashtagResponse::new)
                .collect(Collectors.toList());
        return new HashtagsResponse(hashtagResponses);
    }
}
