package com.example.demo.comment.controller;

import com.example.demo.comment.dto.CommentsResponse;
import com.example.demo.comment.dto.NewCommentRequest;
import com.example.demo.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("picture/comments/{imageId}")
    public ResponseEntity<Void> addComment(@PathVariable(name = "imageId") int imageId,
                                           @Valid @RequestBody NewCommentRequest newCommentRequest) {
        commentService.addComment(imageId, newCommentRequest);
        return ResponseEntity.created(URI.create("/picture/comments")).build();
    }

    @GetMapping("picture/comments/{imageId}")
    public ResponseEntity<CommentsResponse> findComments(@PathVariable(name = "imageId") int imageId) {
        CommentsResponse commentsResponse = commentService.findComments(imageId);
        return ResponseEntity.ok(commentsResponse);
    }


}
