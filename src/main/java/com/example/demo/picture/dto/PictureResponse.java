package com.example.demo.picture.dto;

import com.example.demo.picture.domain.PictureBoard;
import lombok.Getter;

@Getter
public class PictureResponse {
    private int imageId;
    private int userId;
    private String title;
    private String content;
    private String imageUrl;

    public PictureResponse(int imageId, int userId, String title, String content, String imageUrl) {
        this.imageId = imageId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public PictureResponse(PictureBoard pictureBoard) {
        this.imageId = pictureBoard.getImageId();
    }
}
