package com.hackathon.campus.service;

import com.hackathon.campus.dto.ContenedorDTO;
import com.hackathon.campus.model.Contenedor;
import com.hackathon.campus.utils.mappers.ContenedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// Para API externa
@Service
public class ApiContenedorService implements ContenedorService {
    private final RestTemplate restTemplate;
    private final String apiUrl = "http://api-externa/contenedores";

    @Autowired
    public ApiContenedorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ContenedorDTO> findAll() {
        return Arrays.stream(
                        Objects.requireNonNull(restTemplate.getForObject(apiUrl, Contenedor[].class))
                )
                .map(ContenedorMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public ContenedorDTO save(ContenedorDTO contenedor) {
        Contenedor createdContenedor = restTemplate.postForObject(apiUrl, contenedor, Contenedor.class);
        return ContenedorMapper.INSTANCE.toDTO(
                Objects.requireNonNull(createdContenedor)
        );
    }

    @Override
    public ContenedorDTO findById(Long id) {
        Contenedor contenedor = restTemplate.getForObject(apiUrl + "/" + id, Contenedor.class);
        return contenedor != null ? ContenedorMapper.INSTANCE.toDTO(contenedor) : ContenedorDTO.builder().build();
    }
}