package com.example.demo.picture.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;

@Getter
public class NewPictureBoardRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    @NotBlank
    private List<Integer> hashtags;

    @NotBlank
    private String imageUrl;

    public NewPictureBoardRequest(String userId, String title, List<Integer> hashtags, String imageUrl) {
        this.userId = userId;
        this.title = title;
        this.hashtags = hashtags;
        this.imageUrl = imageUrl;
    }
}
