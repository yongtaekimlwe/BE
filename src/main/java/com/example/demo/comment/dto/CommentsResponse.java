package com.example.demo.comment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentsResponse {
    private List<CommentResponse> comments;

    public CommentsResponse() {}

    public CommentsResponse(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
