package com.example.demo.picture.dto;

import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.picture.domain.PictureBoard;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PictureResponse {
    private int imageId;
    private int userId;
    private String title;
    private String content;
    private String imageUrl;
    private  List<HashtagResponse> hashtags;

    public PictureResponse(int imageId, int userId, String title, String content, String imageUrl) {
        this.imageId = imageId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public PictureResponse(PictureBoard pictureBoard) {
        this.imageId = pictureBoard.getImageId();
        this.userId = pictureBoard.getUser().getId();
        this.title = pictureBoard.getTitle();
        this.content = pictureBoard.getContent();
        this.imageUrl = pictureBoard.getImageUrl();
        this.hashtags = pictureBoard.getHashtags()
                .stream()
                .map(HashtagResponse::new).collect(Collectors.toList());
    }
}
