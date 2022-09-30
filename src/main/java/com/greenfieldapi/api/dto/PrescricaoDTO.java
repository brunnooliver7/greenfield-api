package com.greenfieldapi.api.dto;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PrescricaoDTO {

  private Long id;

  @NotNull
  private Long medicoId;

  @NotNull
  private Long pacienteId;

  @Valid
  private List<MedicamentoDTO> medicamentos;

  private OffsetDateTime dtCadastro;

  private OffsetDateTime dtAtualizacao;

}
