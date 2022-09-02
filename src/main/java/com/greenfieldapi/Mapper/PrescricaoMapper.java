package com.greenfieldapi.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.DTO.PrescricaoDTO;
import com.greenfieldapi.Model.Prescricao;

@Mapper(uses = { MedicamentoMapper.class })
public interface PrescricaoMapper {

  public PrescricaoMapper INSTANCE = Mappers.getMapper(PrescricaoMapper.class);

  PrescricaoDTO toDTO(Prescricao entity);
  List<PrescricaoDTO> toDTOs(List<Prescricao> entity);

  @Mapping(target = "medico", ignore = true)
  @Mapping(target = "paciente", ignore = true)
  Prescricao toEntity(PrescricaoDTO dto);
}
