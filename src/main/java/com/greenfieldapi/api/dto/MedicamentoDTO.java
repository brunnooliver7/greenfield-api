package com.greenfieldapi.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentoDTO {
  private Long id;

  @NotBlank
  private String descricao;
  
  @Positive
  private Integer quantidade;
  
  private String dosagem;
  
  private String frequencia;
}
