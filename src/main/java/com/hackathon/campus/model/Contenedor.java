package com.hackathon.campus.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "contenedores")
public class Contenedor {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ubicacion;
    private String tipo;
    private int nivel;
    @Column(name = "ultimo_vaciado")
    private LocalDateTime ultimoVaciado;

}