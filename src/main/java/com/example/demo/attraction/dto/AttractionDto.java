package com.example.demo.attraction.dto;

import com.example.demo.attraction.domain.Attraction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {
    private int contentId;
    private String title;
    private String addr;
    private String img1;
    private String img2;
    private SidoDto sido;
    private GugunDto gugun;
    private float latitude;
    private float longitude;

    public AttractionDto(Attraction attraction) {
        this.contentId = attraction.getContentId();
        this.title = attraction.getTitle();
        this.addr = attraction.getAddr();
        this.img1 = attraction.getImg1();
        this.img2 = attraction.getImg2();
//        this.sido = new SidoDto(attraction.getSido());
//        this.gugun = new GugunDto(attraction.getGugun());
        this.latitude = attraction.getLatitude();
        this.longitude = attraction.getLongitude();
    }

    public AttractionDto(int id, String title, String addr, float latitude, float longitude) {
        this.contentId = id;
        this.title = title;
        this.addr = addr;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}