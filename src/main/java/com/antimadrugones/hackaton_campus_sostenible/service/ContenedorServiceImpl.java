package com.antimadrugones.hackaton_campus_sostenible.service;

import com.antimadrugones.hackaton_campus_sostenible.dto.ContenedorDTO;
import com.antimadrugones.hackaton_campus_sostenible.repository.ContenedorRepository;
import com.antimadrugones.hackaton_campus_sostenible.utils.mappers.ContenedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenedorServiceImpl implements ContenedorService {
    private final ContenedorRepository repository;

    @Autowired
    public ContenedorServiceImpl(ContenedorRepository repository) {
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

    @Override
    public ContenedorDTO findById(String id) {
        return repository.findById(id)
                .map(ContenedorMapper.INSTANCE::toDTO)
                .orElse(ContenedorDTO.builder().build());
    }
}
