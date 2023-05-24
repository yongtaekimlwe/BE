package com.example.demo.picture.controller;

import com.example.demo.picture.dto.NewPictureBoardRequest;
import com.example.demo.picture.dto.NewPictureBoardResponse;
import com.example.demo.picture.dto.PictureResponse;
import com.example.demo.picture.dto.PicturesResponse;
import com.example.demo.picture.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/picture")
public class PictureController {
    private final PictureService pictureService;
    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

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
        logger.debug("Post 객체 확인", newPictureBoardRequest);
        NewPictureBoardResponse pictureBoard = pictureService.createPictureBoard(newPictureBoardRequest);
        return ResponseEntity.created(URI.create("/picture/" + pictureBoard.getImageId())).build();
    }

    @DeleteMapping(path = "/{imageId}")
    public ResponseEntity<Void> deletePictureBoard(@PathVariable("imageId") int imageId) {
        pictureService.deletePictureBoard(imageId);
        return ResponseEntity.noContent().build();
    }
}
