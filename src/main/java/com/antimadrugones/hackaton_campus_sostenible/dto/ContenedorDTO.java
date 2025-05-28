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
    private Long id;
    private String ubicacion;
    private String tipo;
    private int nivel;
    private String ultimoVaciado;
}
