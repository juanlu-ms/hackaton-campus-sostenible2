package com.antimadrugones.hackaton_campus_sostenible.repository;

import com.antimadrugones.hackaton_campus_sostenible.model.Contenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenedorRepository extends JpaRepository<Contenedor, String> {
}
