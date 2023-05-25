package com.example.demo.hashtag.dto;

import com.example.demo.hashtag.domain.Hashtag;
import lombok.Getter;


@Getter
public class HashtagResponse {
    private String tagName;
    private String tagIcon;

    public HashtagResponse(String tagName, String tagIcon) {
        this.tagName = tagName;
        this.tagIcon = tagIcon;
    }

    public HashtagResponse(Hashtag hashTag) {
        this.tagName = hashTag.getTagName();
        this.tagIcon = hashTag.getTagIcon();
    }
}
