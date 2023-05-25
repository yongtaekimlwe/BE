package com.example.demo.hashtag.domain;

import com.example.demo.picture.domain.PictureBoard;
<<<<<<< HEAD:src/main/java/com/example/demo/hashtag/domain/Hashtag.java
=======
import com.example.demo.route.domain.Route;
>>>>>>> feature/4:src/main/java/com/example/demo/picture/domain/Hashtag.java
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity(name = "hashtag")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "tag_id")
    private int tagId;

    @Column(name="tag_name")
    private String tagName;

    @Column(name="tag_icon")
    private String tagIcon;

    public Hashtag() { }

    @ManyToMany(mappedBy = "hashtags")
    private List<PictureBoard> imageBoards;

    @ManyToMany(mappedBy = "routeHashtags")
    private List<Route> routes;

    @Builder
    public Hashtag(int tagId, String tagName, String tagIcon) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagIcon = tagIcon;
    }
}
