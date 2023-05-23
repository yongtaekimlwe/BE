package com.example.demo.picture.controller;

import com.example.demo.picture.service.PictureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }
    //image_board, image_board_comment, image_board_hashtag, hashtag, user_image_like
}
