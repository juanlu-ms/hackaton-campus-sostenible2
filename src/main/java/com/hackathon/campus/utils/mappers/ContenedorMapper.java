package com.hackathon.campus.utils.mappers;

import com.hackathon.campus.dto.ContenedorDTO;
import com.hackathon.campus.model.Contenedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContenedorMapper {

    ContenedorMapper INSTANCE = Mappers.getMapper(ContenedorMapper.class);

    ContenedorDTO toDTO(Contenedor contenedor);

    Contenedor toEntity(ContenedorDTO contenedorDTO);
}