package com.example.demo.user.domain;

import com.example.demo.picture.domain.PictureBoard;
import com.example.demo.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id; // PK > auto increment

    @Column(name = "user_pw")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "profile_img_src")
    private String imgSrc;

    @ManyToMany
    @JoinTable(
            name = "user_image_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<PictureBoard> likedImages;

    @Builder
    public User(int id) {
        this.id = id;
    }

    @Builder // Builder 클래스 자동 생성 > 생성자 대신 사용
    public User(String password, String name, String email, String imgSrc) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.imgSrc = imgSrc;
    }

    public UserDto toDto(User entity) {
        return new UserDto(entity.id, entity.name, entity.password,entity.email,entity.imgSrc);
    }
}
