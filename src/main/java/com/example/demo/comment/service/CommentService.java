package com.example.demo.comment.service;

import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.dto.CommentResponse;
import com.example.demo.comment.dto.CommentsResponse;
import com.example.demo.comment.dto.NewCommentRequest;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.picture.domain.PictureBoard;
import com.example.demo.picture.repository.PictureBoardRepository;
import com.example.demo.picture.service.PictureService;
import com.example.demo.user.domain.User;
import com.example.demo.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PictureBoardRepository pictureBoardRepository;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(PictureService.class);

    public CommentService(CommentRepository commentRepository, PictureBoardRepository pictureBoardRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.pictureBoardRepository = pictureBoardRepository;
        this.userRepository = userRepository;
    }

    public CommentsResponse findComments(int imageId) {
        List<Comment> comments = commentRepository.findCommentsByImageId(imageId);
        logger.debug("댓글들 찾기 {}", comments);
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            logger.debug("댓글 상태 {}", comment.toString());
            logger.debug("유저 정보 {}", comment.getUser().toString());
            commentResponses.add(CommentResponse.of(comment.getUser(), comment));
        }

        return new CommentsResponse(commentResponses);
    }

    @Transactional
    public void addComment(int imageId, NewCommentRequest newCommentRequest) {
        //먼저 사진 공유 게시글이 존재하는지 검증
        Optional<PictureBoard> pictureBoard = pictureBoardRepository.findById(imageId);
        Optional<User> user = userRepository.findById(newCommentRequest.getUserId());

        if (pictureBoard.isPresent() && user.isPresent()) { // 유저 id가 존재하고 사진 게시글 id가 존재한다면
            Comment comment = Comment.builder()
                    .pictureBoard(pictureBoard.get())
                    .user(user.get())
                    .comment(newCommentRequest.getComment())
                    .build();
            logger.debug("게시글이랑 유저 모두 존재");
            commentRepository.save(comment);
        }
    }

    @Transactional
    public void deleteComment(int commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            logger.debug("댓글 존재해서 삭제될 예정");
            commentRepository.delete(comment.get());
        }
    }
}
