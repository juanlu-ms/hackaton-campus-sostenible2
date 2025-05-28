package com.antimadrugones.hackaton_campus_sostenible.service;

import com.antimadrugones.hackaton_campus_sostenible.dto.ContenedorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContenedorService {
    List<ContenedorDTO> findAll();
    ContenedorDTO save(ContenedorDTO contenedor);
    ContenedorDTO findById(String id);
}