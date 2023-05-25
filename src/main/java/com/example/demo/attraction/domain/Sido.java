package com.example.demo.attraction.domain;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

@Getter
@Entity(name="sido")
@EntityListeners(AuditingEntityListener.class)
public class Sido {

    @Id
    @Column(name = "sido_code")
    private int code;

    private String name;
}
