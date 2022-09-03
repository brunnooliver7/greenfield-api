package com.greenfieldapi.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.api.dto.MedicamentoDTO;
import com.greenfieldapi.domain.model.Medicamento;

@Mapper
public interface MedicamentoMapper {

  public MedicamentoMapper INSTANCE = Mappers.getMapper(MedicamentoMapper.class);

  MedicamentoDTO toDTO(Medicamento medicamento);
  
  Medicamento toEntity(MedicamentoDTO dto);
}
