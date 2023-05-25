package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String password;
    private String name;
    private String email;
    private String imgSrc;

    public UserDto(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.imgSrc = user.getImgSrc();
    }

    public UserDto(String name, String email, String imgSrc) {
        this.name = name;
        this.email = email;
        this.imgSrc = imgSrc;
    }
}
