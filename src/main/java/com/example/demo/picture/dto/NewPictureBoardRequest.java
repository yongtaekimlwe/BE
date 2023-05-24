package com.example.demo.picture.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;

import java.util.List;

@Getter
public class NewPictureBoardRequest {
    @NotNull
    private int userId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotEmpty
    private List<Integer> hashtags;

    @NotBlank
    private String imageUrl;

    public NewPictureBoardRequest() {
    }

    public NewPictureBoardRequest(int userId, String title, String content, List<Integer> hashtags, String imageUrl) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
        this.imageUrl = imageUrl;
    }
}
