package com.example.demo.comment.domain;

import com.example.demo.picture.domain.PictureBoard;
import com.example.demo.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Entity(name="image_board_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private PictureBoard pictureBoard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(name = "regist_time")
    private LocalDateTime createdAt;

    @Column(name = "comment")
    private String comment;

    public Comment() { }

    @Builder
    public Comment(int commentId, PictureBoard pictureBoard, User user, LocalDateTime createdAt, String comment) {
        this.commentId = commentId;
        this.pictureBoard = pictureBoard;
        this.user = user;
        this.createdAt = createdAt;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", pictureBoard=" + pictureBoard +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", comment='" + comment + '\'' +
                '}';
    }
}
