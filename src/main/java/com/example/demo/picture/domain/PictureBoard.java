package com.example.demo.picture.domain;

import com.example.demo.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name="image_board")
@EntityListeners(AuditingEntityListener.class)
public class PictureBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    private String title;

    private String content;

    @Column(name="image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "image_board_hashtag",
            joinColumns = @JoinColumn(name = "image_board_image_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_tag_id"))
    private List<Hashtag> hashtags = new ArrayList<>();

    @ManyToMany(mappedBy = "likedImages", cascade = CascadeType.ALL)
    private List<User> likedByUsers;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void updateHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public PictureBoard() {
    }

    @Builder
    public PictureBoard(int imageId, String title, String content, String imageUrl, User user, List<Hashtag> hashtags, List<User> likedByUsers) {
        this.imageId = imageId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = user;
        this.hashtags = hashtags;
        this.likedByUsers = likedByUsers;
    }
}
