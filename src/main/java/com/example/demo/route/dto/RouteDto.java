package com.example.demo.route.dto;

import com.example.demo.route.domain.Route;
import com.example.demo.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private int id;
    private UserDto user;
    private String title;
    private String content;

    public RouteDto(Route route) {
        this.id = route.getId();
        this.user = new UserDto(route.getUser());
        this.title = route.getTitle();
        this.content = route.getContent();
    }

}
