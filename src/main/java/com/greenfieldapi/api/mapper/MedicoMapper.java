package com.greenfieldapi.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.api.dto.MedicoDTO;
import com.greenfieldapi.domain.model.Medico;

@Mapper
public interface MedicoMapper {
  
  public MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);

  MedicoDTO toDTO(Medico entity);
  List<MedicoDTO> toDTOs(List<Medico> entities);

  @Mapping(target = "prescricoes", ignore = true)
  Medico toEntity(MedicoDTO dto);
}
