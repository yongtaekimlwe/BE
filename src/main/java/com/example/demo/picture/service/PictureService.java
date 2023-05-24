package com.example.demo.picture.service;

import com.example.demo.picture.domain.HashTag;
import com.example.demo.picture.domain.PictureBoard;
import com.example.demo.picture.dto.NewPictureBoardRequest;
import com.example.demo.picture.dto.NewPictureBoardResponse;
import com.example.demo.picture.dto.PictureResponse;
import com.example.demo.picture.dto.PicturesResponse;
import com.example.demo.picture.repository.HashTagRepository;
import com.example.demo.picture.repository.PictureBoardRepository;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PictureService {

    private final PictureBoardRepository pictureBoardRepository;
    private final UserRepository userRepository;

    private final HashTagRepository hashTagRepository;

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

    public Optional<PictureResponse> findPicture(int imageId) {
        return pictureBoardRepository.findById(imageId)
                .map(PictureResponse::new);
    }

    @Transactional
    public NewPictureBoardResponse createPictureBoard(NewPictureBoardRequest newPictureBoardRequest) {
        Optional<User> userOptional = userRepository.findById(newPictureBoardRequest.getUserId());
        List<HashTag> hashTags = hashTagRepository.findAllById(newPictureBoardRequest.getHashtags());

        if(userOptional.isPresent() && hashTags.size() == newPictureBoardRequest.getHashtags().size()) {
            User user = userOptional.get();
            PictureBoard pictureBoard = PictureBoard.builder()
                    .title(newPictureBoardRequest.getTitle())
                    .content(newPictureBoardRequest.getContent())
                    .imageUrl(newPictureBoardRequest.getImageUrl())
                    .user(user)
                    .hashtags(hashTags)
                    .build();
            //해시태그 설정 로직 필요
            PictureBoard savePictureBoard = pictureBoardRepository.save(pictureBoard);
            return new NewPictureBoardResponse(savePictureBoard);
        } else {
            return new NewPictureBoardResponse(-1);
        }
    }

    @Transactional
    public void deletePictureBoard(int imageId) {
        //게시판 삭제되어야함, 게시판과 관련된 image_board_hashtag, 게시판고 관련된 댓글, 게시판과 관련된 좋아요
        pictureBoardRepository.delete(PictureBoard.builder().imageId(imageId).build());
    }
}
