package com.greenfieldapi.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentoDTO {
  private Long id;
  private String descricao;
  private Integer quantidade;
  private String dosagem;
  private String frequencia;
}
