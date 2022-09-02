package com.greenfieldapi.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescricaoDTO {
  private Long id;
  private Long medicoId;
  private Long pacienteId;
  private List<MedicamentoDTO> medicamentos;
}
