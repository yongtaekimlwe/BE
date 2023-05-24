package com.example.demo.route.dto;


import com.example.demo.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailRouteInfo {
    private String content;
    private List<UserDto> participants;
//    private List<AttractionDto> attractions;
}