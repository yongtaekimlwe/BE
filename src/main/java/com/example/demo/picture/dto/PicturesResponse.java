package com.example.demo.picture.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PicturesResponse {
    private List<PictureResponse> pictures;

    public PicturesResponse(List<PictureResponse> pictures) {
        this.pictures = pictures;
    }
}
