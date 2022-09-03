package com.greenfieldapi.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.api.dto.PacienteDTO;
import com.greenfieldapi.domain.model.Paciente;

@Mapper
public interface PacienteMapper {
  
  public PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

  PacienteDTO toDTO(Paciente entity);
  List<PacienteDTO> toDTOs(List<Paciente> entities);

  @Mapping(target = "prescricoes", ignore = true)
  Paciente toEntity(PacienteDTO dto);
}
