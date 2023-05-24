package com.example.demo.comment.controller;

import com.example.demo.comment.dto.CommentsResponse;
import com.example.demo.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("picture/comments/{imageId}")
    public ResponseEntity<CommentsResponse> findComments(@PathVariable(name = "imageId") int imageId) {
        CommentsResponse commentsResponse = commentService.findComments(imageId);
        return ResponseEntity.ok(commentsResponse);
    }

}
