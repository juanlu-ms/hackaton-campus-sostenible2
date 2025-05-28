/*package com.antimadrugones.hackaton_campus_sostenible.service;

import com.antimadrugones.hackaton_campus_sostenible.dto.ContenedorDTO;
import com.antimadrugones.hackaton_campus_sostenible.model.Contenedor;
import com.antimadrugones.hackaton_campus_sostenible.utils.mappers.ContenedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Para API externa
@Service
public class ApiContenedorService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://api-externa/contenedores";

    @Autowired
    public ApiContenedorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ContenedorDTO> findAll() {
        return Arrays.stream(
                        Objects.requireNonNull(restTemplate.getForObject(apiUrl, Contenedor[].class))
                )
                .map(ContenedorMapper.INSTANCE::toDTO)
                .toList();
    }

    public ContenedorDTO save(ContenedorDTO contenedor) {
        Contenedor createdContenedor = restTemplate.postForObject(apiUrl, contenedor, Contenedor.class);
        return ContenedorMapper.INSTANCE.toDTO(
                Objects.requireNonNull(createdContenedor)
        );
    }

    public ContenedorDTO findById(Long id) {
        Contenedor contenedor = restTemplate.getForObject(apiUrl + "/" + id, Contenedor.class);
        return contenedor != null ? ContenedorMapper.INSTANCE.toDTO(contenedor) : ContenedorDTO.builder().build();
    }
}*/