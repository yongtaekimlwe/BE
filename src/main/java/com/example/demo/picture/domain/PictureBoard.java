package com.example.demo.picture.domain;

import com.example.demo.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "image_board_hashtag",
            joinColumns = @JoinColumn(name = "image_board_image_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_tag_id"))
    private Set<HashTag> hashtags;

    @ManyToMany(mappedBy = "likedImages")
    private List<User> likedByUsers;

    public PictureBoard() {
    }
    @Builder
    public PictureBoard(String title, String content, String imageUrl, int userId, List<Integer> hashtags) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = User.builder().id(userId).build();
        this.hashtags = new HashSet<>();

        for (int tagId : hashtags) {
            this.hashtags.add(HashTag.builder().tagId(tagId).build());
        }
    }
}
