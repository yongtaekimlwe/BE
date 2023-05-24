package com.example.demo.hashtag.service;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashtagService {

    private final HashTagRepository hashTagRepository;

    public List<HashtagResponse> getHashtagResponses(List<Hashtag> hashtags) {
        return hashtags.stream()
                .map(hashtag -> new HashtagResponse(hashtag.getTagName(), hashtag.getTagIcon()))
                .collect(Collectors.toList());
    }

    public List<HashtagResponse> getAllHashtags() {
        return hashTagRepository.findAll()
                .stream()
                .map(HashtagResponse::new)
                .collect(Collectors.toList());
    }
}
