package com.antimadrugones.hackaton_campus_sostenible.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "contenedores")
public class Contenedor {
    @Id
    private String id;
    private String type;
    private String center;
    private String location;
    private Double latitude;
    private Double longitude;
    private int capacity;
    private String unit;
}