package com.antimadrugones.hackaton_campus_sostenible.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContenedorDTO {
    private String id;
    private String type;
    private String center;
    private String location;
    private Double latitude;
    private Double longitude;
    private int capacity;
    private String unit;
}
