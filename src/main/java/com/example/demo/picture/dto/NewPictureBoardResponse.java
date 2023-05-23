package com.example.demo.picture.dto;

import com.example.demo.picture.domain.PictureBoard;

public class NewPictureBoardResponse {
    private int id;

    public NewPictureBoardResponse(int id) {
        this.id = id;
    }

    public NewPictureBoardResponse(PictureBoard pictureBoard) {
        this.id = pictureBoard.getImageId();
    }

    public int getImageId() {
        return id;
    }
}
