package com.greenfieldapi.api.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {

  private Long id;

  @NotBlank
  @CPF
  private String cpf;

  @NotBlank
  private String nome;

  @NotNull
  @PastOrPresent
  private LocalDate dtNascimento;

  @NotNull
  @Size(min = 1)
  private String sexo;

  private OffsetDateTime dtCadastro;

  private OffsetDateTime dtAtualizacao;

}
