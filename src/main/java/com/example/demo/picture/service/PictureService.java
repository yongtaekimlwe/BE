package com.example.demo.picture.service;

import com.example.demo.picture.domain.Hashtag;
import com.example.demo.picture.domain.PictureBoard;
import com.example.demo.picture.dto.*;
import com.example.demo.picture.repository.HashTagRepository;
import com.example.demo.picture.repository.PictureBoardRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PictureService {

    private final PictureBoardRepository pictureBoardRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;
    Logger logger = LoggerFactory.getLogger(PictureService.class);

    public PictureService(PictureBoardRepository pictureBoardRepository, UserRepository userRepository, HashTagRepository hashTagRepository) {
        this.pictureBoardRepository = pictureBoardRepository;
        this.userRepository = userRepository;
        this.hashTagRepository = hashTagRepository;
    }

    public PicturesResponse findPictures() {
        List<PictureResponse> pictureResponses = pictureBoardRepository.findAll()
                .stream()
                .map(PictureResponse::new)
                .collect(Collectors.toList());
        return new PicturesResponse(pictureResponses);
    }

    public Optional<PictureDetailResponse> findPicture(int imageId) {
        Optional<PictureBoard> pictureBoardOptional = pictureBoardRepository.findByIdWithHashTags(imageId);
        if (pictureBoardOptional.isPresent()) {
            PictureBoard pictureBoard = pictureBoardOptional.get();
            List<HashtagResponse> hashtagsResponse = this.getHashtagResponses(pictureBoard.getHashtags());

            return Optional.of(PictureDetailResponse.of(pictureBoard.getImageId(),
                    pictureBoard.getUser().getId(),
                    pictureBoard.getTitle(),
                    pictureBoard.getContent(),
                    pictureBoard.getImageUrl(),
                    hashtagsResponse));
        }
        return Optional.empty();
    }

    private List<HashtagResponse> getHashtagResponses(List<Hashtag> hashtags) {
        return hashtags.stream()
                .map(hashtag -> new HashtagResponse(hashtag.getTagName(), hashtag.getTagIcon()))
                .collect(Collectors.toList());
    }

    @Transactional
    public NewPictureBoardResponse createPictureBoard(NewPictureBoardRequest newPictureBoardRequest) {
        Optional<User> userOptional = userRepository.findById(newPictureBoardRequest.getUserId());
        List<Hashtag> hashtags = hashTagRepository.findAllById(newPictureBoardRequest.getHashtags());

        if(userOptional.isPresent() && hashtags.size() == newPictureBoardRequest.getHashtags().size()) {
            User user = userOptional.get();
            PictureBoard pictureBoard = PictureBoard.builder()
                    .title(newPictureBoardRequest.getTitle())
                    .content(newPictureBoardRequest.getContent())
                    .imageUrl(newPictureBoardRequest.getImageUrl())
                    .user(user)
                    .hashtags(hashtags)
                    .build();
            //해시태그 설정 로직 필요
            PictureBoard savePictureBoard = pictureBoardRepository.save(pictureBoard);
            return new NewPictureBoardResponse(savePictureBoard);
        } else {
            return new NewPictureBoardResponse(-1);
        }
    }

    @Transactional
    public void updatePictureBoard(int imageId, PictureBoardUpdateRequest pictureBoardUpdateRequest) {
        //imageId가 존재하는 게시글인지 검증 유무, pictureBoardUpdateRequest 내용에 맞게 적용
        PictureBoard pictureBoard = pictureBoardRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시물"));

        List<Hashtag> hashtags = hashTagRepository.findAllById(pictureBoardUpdateRequest.getHashtags());

        pictureBoard.updateTitle(pictureBoardUpdateRequest.getTitle());
        pictureBoard.updateContent(pictureBoardUpdateRequest.getContent());
        pictureBoard.updateImageUrl(pictureBoard.getImageUrl());

        pictureBoard.getHashtags().clear();
        pictureBoard.getHashtags().addAll(hashtags);
        pictureBoardRepository.save(pictureBoard);
    }

    @Transactional
    public void deletePictureBoard(int imageId) {
        //게시판 삭제되어야함, 게시판과 관련된 image_board_hashtag, 게시판고 관련된 댓글, 게시판과 관련된 좋아요
        pictureBoardRepository.delete(PictureBoard.builder().imageId(imageId).build());
    }
}
