package com.antimadrugones.hackaton_campus_sostenible.utils.mappers;


import com.antimadrugones.hackaton_campus_sostenible.dto.ContenedorDTO;
import com.antimadrugones.hackaton_campus_sostenible.model.Contenedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContenedorMapper {

    ContenedorMapper INSTANCE = Mappers.getMapper(ContenedorMapper.class);

    ContenedorDTO toDTO(Contenedor contenedor);

    Contenedor toEntity(ContenedorDTO contenedorDTO);
}