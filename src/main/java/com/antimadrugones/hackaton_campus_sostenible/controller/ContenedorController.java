package com.antimadrugones.hackaton_campus_sostenible.controller;

import com.antimadrugones.hackaton_campus_sostenible.dto.ContenedorDTO;
import com.antimadrugones.hackaton_campus_sostenible.service.ContenedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContenedorController {

    private final ContenedorService contenedorService;

    @Autowired
    public ContenedorController(ContenedorService contenedorService) {
        this.contenedorService = contenedorService;
    }

    @GetMapping("/contenedores")
    public List<ContenedorDTO> getAllContenedores() {
        return contenedorService.findAll();
    }

    @GetMapping("/contenedores/{id}")
    public ContenedorDTO getContenedorById(@PathVariable String id) {
        return contenedorService.findById(id);
    }
}