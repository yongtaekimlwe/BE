package com.example.demo.route.dto;

import com.example.demo.hashtag.dto.HashtagResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BriefRouteInfo {
    private String title;
    private String userName;
    private int likes;
    private List<HashtagResponse> hashtags;
}
