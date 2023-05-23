package com.example.demo.picture.service;

import com.example.demo.picture.dto.PictureResponse;
import com.example.demo.picture.dto.PicturesResponse;
import com.example.demo.picture.repository.PictureBoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PictureService {

    private final PictureBoardRepository pictureBoardRepository;

    public PictureService(PictureBoardRepository pictureBoardRepository) {
        this.pictureBoardRepository = pictureBoardRepository;
    }

    public PicturesResponse findPictures() {
        List<PictureResponse> pictureResponses = pictureBoardRepository.findAll()
                .stream()
                .map(PictureResponse::new)
                .collect(Collectors.toList());
        return new PicturesResponse(pictureResponses);
    }

    public Optional<PictureResponse> findPicture(int imageId) {
        return pictureBoardRepository.findById(imageId)
                .map(PictureResponse::new);
    }
}
