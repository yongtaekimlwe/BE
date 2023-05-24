package com.example.demo.picture.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity(name = "hashtag")
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "tag_id")
    private int tagId;

    @Column(name="tag_name")
    private String tagName;

    @Column(name="tag_icon")
    private String tagIcon;

    @ManyToMany(mappedBy = "hashtags")
    private List<PictureBoard> imageBoards;

    public HashTag() { }

    @Builder
    public HashTag(int tagId) {
        this.tagId = tagId;
    }

    @Builder
    public HashTag(String tagName, String tagIcon) {
        this.tagName = tagName;
        this.tagIcon = tagIcon;
    }
}
