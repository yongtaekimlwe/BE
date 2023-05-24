package com.example.demo.picture.dto;

import com.example.demo.hashtag.dto.HashtagResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PictureDetailResponse {
    private int imageId;
    private int userId;
    private String title;
    private String content;
    private String imageUrl;
    private List<HashtagResponse> hashtags;


   @Builder
   public PictureDetailResponse(int imageId, int userId, String title, String content, String imageUrl, List<HashtagResponse> hashtags) {
       this.imageId = imageId;
       this.userId = userId;
       this.title = title;
       this.content = content;
       this.imageUrl = imageUrl;
       this.hashtags = hashtags;
   }

   public static PictureDetailResponse of(int imageId, int userId, String title, String content, String imageUrl, List<HashtagResponse> hashtags) {
       return new PictureDetailResponse(imageId, userId, title, content, imageUrl, hashtags);
   }
}
