package com.example.demo.picture.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@Getter
@Entity
@Table(name="image_board")
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

    //    @ManyToOne
//    @JoinColumn(name= "user_id")
//    private User user;

//    @ManyToMany
//    @JoinTable(name = "user_image_like",
//            joinColumns = @JoinColumn(name = "image_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
    //private Set<User> likedByUsers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "image_board_hashtag",
            joinColumns = @JoinColumn(name = "image_board_image_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_tag_id"))
    private List<HashTag> hashtags;

    public PictureBoard() {
    }
    @Builder
    public PictureBoard(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }
}
