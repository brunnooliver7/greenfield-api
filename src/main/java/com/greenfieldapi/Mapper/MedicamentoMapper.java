package com.greenfieldapi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.DTO.MedicamentoDTO;
import com.greenfieldapi.Model.Medicamento;

@Mapper
public interface MedicamentoMapper {

  public MedicamentoMapper INSTANCE = Mappers.getMapper(MedicamentoMapper.class);

  MedicamentoDTO toDTO(Medicamento medicamento);
  
  Medicamento toEntity(MedicamentoDTO dto);
}
