package com.example.demo.comment.service;

import com.example.demo.comment.domain.Comment;
import com.example.demo.comment.dto.CommentResponse;
import com.example.demo.comment.dto.CommentsResponse;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.picture.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    Logger logger = LoggerFactory.getLogger(PictureService.class);

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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
}
