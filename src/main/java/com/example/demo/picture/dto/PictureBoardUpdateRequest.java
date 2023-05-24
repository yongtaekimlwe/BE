package com.example.demo.picture.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class PictureBoardUpdateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotEmpty
    private List<Integer> hashtags;

    @NotBlank
    private String imageUrl;

    public PictureBoardUpdateRequest() {
    }

    public PictureBoardUpdateRequest(String title, String content, List<Integer> hashtags, String imageUrl) {
        this.title = title;
        this.content = content;
        this.hashtags = hashtags;
        this.imageUrl = imageUrl;
    }
}
