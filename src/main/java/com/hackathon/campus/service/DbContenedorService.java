package com.hackathon.campus.service;

import com.hackathon.campus.dto.ContenedorDTO;
import com.hackathon.campus.repository.ContenedorRepository;
import com.hackathon.campus.utils.mappers.ContenedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbContenedorService implements ContenedorService {
    private final ContenedorRepository repository;

    @Autowired
    public DbContenedorService(ContenedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ContenedorDTO> findAll() {
        return repository.findAll().stream()
                .map(ContenedorMapper.INSTANCE::toDTO)
                .toList();
    }

    @Override
    public ContenedorDTO save(ContenedorDTO contenedor) {
        return ContenedorMapper.INSTANCE.toDTO(
                repository.save(ContenedorMapper.INSTANCE.toEntity(contenedor)
                )
        );
    }


    public ContenedorDTO findById(Long id) {
        return repository.findById(id)
                .map(ContenedorMapper.INSTANCE::toDTO)
                .orElse(ContenedorDTO.builder().build());
    }
}
