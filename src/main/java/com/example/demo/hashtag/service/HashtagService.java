package com.example.demo.hashtag.service;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.hashtag.domain.Hashtag;
<<<<<<< HEAD
import com.example.demo.hashtag.dto.HashtagsResponse;
import com.example.demo.hashtag.repository.HashtagRepository;
=======
import com.example.demo.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
>>>>>>> feature/4
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HashtagService {

<<<<<<< HEAD
    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }
=======
    private final HashTagRepository hashTagRepository;
>>>>>>> feature/4

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

    public List<HashtagResponse> getAllHashtags() {
        return hashTagRepository.findAll()
                .stream()
                .map(HashtagResponse::new)
                .collect(Collectors.toList());
    }
}
