package com.example.demo.hashtag.dto;

import com.example.demo.hashtag.domain.Hashtag;
import lombok.Getter;


@Getter
public class HashtagResponse {

    private int tagId;
    private String tagName;
    private String tagIcon;

    public HashtagResponse(int tagId, String tagName, String tagIcon) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagIcon = tagIcon;
    }

    public HashtagResponse(Hashtag hashTag) {
        this.tagId = hashTag.getTagId();
        this.tagName = hashTag.getTagName();
        this.tagIcon = hashTag.getTagIcon();
    }
}
