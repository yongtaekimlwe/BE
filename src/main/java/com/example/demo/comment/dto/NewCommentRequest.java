package com.example.demo.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class NewCommentRequest {
    @NotNull
    private int userId;

    @NotBlank(message = "댓글은 1자 이상 45자 이하여야 합니다.")
    private String comment;

    public NewCommentRequest() { }


}
