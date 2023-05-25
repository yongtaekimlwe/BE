package com.example.demo.route.dto;


import com.example.demo.hashtag.dto.HashtagResponse;
import com.example.demo.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailRouteInfo {
    private int routeId;
    private String title;
    private String userName;
    private int likes;
    private List<Map<String, String>> hashtags;
    private String content;
    private List<UserDto> participants;
//    private List<AttractionDto> attractions;
}