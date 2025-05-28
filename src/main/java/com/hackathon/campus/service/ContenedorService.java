package com.hackathon.campus.service;

import com.hackathon.campus.dto.ContenedorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContenedorService {
    List<ContenedorDTO> findAll();
    ContenedorDTO save(ContenedorDTO contenedor);
    ContenedorDTO findById(Long id);
}