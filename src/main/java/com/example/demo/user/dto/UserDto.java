package com.example.demo.user.dto;

import com.example.demo.user.domain.User;
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

    public User toEntity() {
        return User.builder()
                .password(password)
                .name(name)
                .email(email)
                .imgSrc(imgSrc)
                .build();
    }
}
