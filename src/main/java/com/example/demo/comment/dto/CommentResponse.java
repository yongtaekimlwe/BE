package com.example.demo.comment.dto;

import com.example.demo.comment.domain.Comment;
import com.example.demo.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final int commendId;
    private final String nickname;
    private final String content;
    private final LocalDateTime createdAt;

    public CommentResponse(int commendId, String nickname, String content, LocalDateTime createdAt) {
        this.commendId = commendId;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentResponse of(User user, Comment comment) {
        return new CommentResponse(comment.getCommentId(), user.getName(), comment.getComment(), comment.getCreatedAt());
    }
}
