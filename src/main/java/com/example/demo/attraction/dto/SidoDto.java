package com.example.demo.attraction.dto;

import com.example.demo.attraction.domain.Sido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SidoDto {
    private int code;
    private String name;

    public SidoDto(Sido sido) {
        this.code = sido.getCode();
        this.name = sido.getName();
    }
}
