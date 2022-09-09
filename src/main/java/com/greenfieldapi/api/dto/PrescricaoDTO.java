package com.greenfieldapi.api.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescricaoDTO {
  private Long id;

  @NotNull
  private Long medicoId;

  @NotNull
  private Long pacienteId;
  
  private List<MedicamentoDTO> medicamentos;
}
