/*package com.antimadrugones.hackaton_campus_sostenible.service;

import com.antimadrugones.hackaton_campus_sostenible.dto.ContenedorDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MockContenedorService implements ContenedorService {

    @Override
    public List<ContenedorDTO> findAll() {
        return Arrays.asList(
                ContenedorDTO.builder()
                        .id(1L)
                        .ubicacion("Biblioteca")
                        .tipo("Papel")
                        .nivel(95)
                        .ultimoVaciado(LocalDateTime.now().toString())
                        .build(),
                ContenedorDTO.builder()
                        .id(2L)
                        .ubicacion("Cafetería")
                        .tipo("Orgánico")
                        .nivel(87)
                        .ultimoVaciado(LocalDateTime.now().toString())
                        .build(),
                ContenedorDTO.builder()
                        .id(3L)
                        .ubicacion("Aula A")
                        .tipo("Plástico")
                        .nivel(72)
                        .ultimoVaciado(LocalDateTime.now().toString())
                        .build(),
                ContenedorDTO.builder()
                        .id(4L)
                        .ubicacion("Gimnasio")
                        .tipo("General")
                        .nivel(45)
                        .ultimoVaciado(LocalDateTime.now().toString())
                        .build(),
                ContenedorDTO.builder()
                        .id(5L)
                        .ubicacion("Parking")
                        .tipo("Vidrio")
                        .nivel(23)
                        .ultimoVaciado(LocalDateTime.now().toString())
                        .build());
    }

    @Override
    public ContenedorDTO save(ContenedorDTO contenedor) {
        // Simulación de guardado, en un caso real se guardaría en una base de datos
        if (contenedor.getId() == null) {
            contenedor.setId((long) (Math.random() * 1000)); // Asignar un ID aleatorio
        }
        return contenedor; // Retornar el mismo objeto simulado
    }

    @Override
    public ContenedorDTO findById(Long id) {
        // Simulación de búsqueda, en un caso real se buscaría en una base de datos
        return ContenedorDTO.builder()
                .id(id)
                .ubicacion("Ubicación " + id)
                .tipo("Tipo " + id)
                .nivel((int) (Math.random() * 100))
                .ultimoVaciado(LocalDateTime.now().toString())
                .build();
    }
}*/