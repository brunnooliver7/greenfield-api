package com.greenfieldapi.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.greenfieldapi.api.dto.PrescricaoDTO;
import com.greenfieldapi.domain.model.Prescricao;

@Mapper(uses = { MedicamentoMapper.class })
public interface PrescricaoMapper {

  public PrescricaoMapper INSTANCE = Mappers.getMapper(PrescricaoMapper.class);

  PrescricaoDTO toDTO(Prescricao entity);
  List<PrescricaoDTO> toDTOs(List<Prescricao> entities);

  @Mapping(target = "medico", ignore = true)
  @Mapping(target = "paciente", ignore = true)
  Prescricao toEntity(PrescricaoDTO dto);
}
