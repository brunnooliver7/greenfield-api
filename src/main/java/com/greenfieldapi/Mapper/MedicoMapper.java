package com.greenfieldapi.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.DTO.MedicoDTO;
import com.greenfieldapi.Model.Medico;

@Mapper
public interface MedicoMapper {
  
  public MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

  MedicoDTO toDTO(Medico entity);
  List<MedicoDTO> toDTOs(List<Medico> entities);

  @Mapping(target = "prescricoes", ignore = true)
  Medico toEntity(MedicoDTO dto);
}
