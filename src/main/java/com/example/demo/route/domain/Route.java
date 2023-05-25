package com.example.demo.route.domain;

import com.example.demo.hashtag.domain.Hashtag;
import com.example.demo.user.domain.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name="route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private int id; // PK > auto increment

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "route_hashtag",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Hashtag> routeHashtags;

    @ManyToMany(mappedBy = "likedRoutes")
    private List<User> routeParticipants;

}
