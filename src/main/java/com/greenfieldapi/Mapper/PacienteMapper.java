package com.greenfieldapi.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.DTO.PacienteDTO;
import com.greenfieldapi.Model.Paciente;

@Mapper
public interface PacienteMapper {
  
  public PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

  PacienteDTO toDTO(Paciente entity);
  List<PacienteDTO> toDTOs(List<Paciente> entities);

  @Mapping(target = "prescricoes", ignore = true)
  Paciente toEntity(PacienteDTO dto);
}
