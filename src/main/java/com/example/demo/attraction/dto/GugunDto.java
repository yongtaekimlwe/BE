package com.example.demo.attraction.dto;

import com.example.demo.attraction.domain.Gugun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GugunDto {
    private int code;
    private String name;

    public GugunDto(Gugun gugun) {
        this.code = gugun.getCode();
        this.name = gugun.getName();
    }
}
