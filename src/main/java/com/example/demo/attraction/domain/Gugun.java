package com.example.demo.attraction.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity(name="gugun")
@EntityListeners(AuditingEntityListener.class)
public class Gugun {

    @Id
    @Column(name = "gugun_code")
    private int code;

    private String name;

//    @ManyToOne
//    @JoinColumn(name = "sido_code")
//    private Sido sidoCode;
}