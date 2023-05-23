package com.example.demo.picture.controller;

import com.example.demo.picture.dto.NewPictureBoardRequest;
import com.example.demo.picture.dto.NewPictureBoardResponse;
import com.example.demo.picture.dto.PictureResponse;
import com.example.demo.picture.dto.PicturesResponse;
import com.example.demo.picture.service.PictureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;

    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<PicturesResponse> findPictures() {
        PicturesResponse picturesResponse = pictureService.findPictures();
        return ResponseEntity.ok(picturesResponse);
    }

    @GetMapping(path = "/{imageId}")
    public ResponseEntity<Optional<PictureResponse>> findPictureById(@PathVariable("imageId") int imageId) {
        Optional<PictureResponse> pictureResponse = pictureService.findPicture(imageId);
        return ResponseEntity.ok(pictureResponse);
    }

    @PostMapping()
    public ResponseEntity<Void> createPictureBoard(@Valid @RequestBody NewPictureBoardRequest newPictureBoardRequest) {
        NewPictureBoardResponse pictureBoard = pictureService.createPictureBoard(newPictureBoardRequest);
        return ResponseEntity.created(URI.create("/picture/" + pictureBoard.getImageId())).build();
    }

    //image_board, image_board_comment, image_board_hashtag, hashtag, user_image_like
}
