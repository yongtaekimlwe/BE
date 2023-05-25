package com.example.demo.attraction.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity(name="attraction_info")
@EntityListeners(AuditingEntityListener.class)
public class Attraction {
    @Id
    @Column(name = "content_id")
    private int contentId;

    private String title;

    @Column(name = "addr1")
    private String addr;

    @Column(name = "first_image")
    private String img1;

    @Column(name = "first_image2")
    private String img2;

//    @ManyToOne
//    @JoinColumn(name = "sido_code")
//    private Sido sido;
//
//    @ManyToOne
//    @JoinColumn(name = "gugun_code")
//    private Gugun gugun;

    private float latitude;
    private float longitude;

}
