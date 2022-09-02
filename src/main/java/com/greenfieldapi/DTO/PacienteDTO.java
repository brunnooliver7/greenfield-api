package com.greenfieldapi.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {
  private Long id;
  private String cpf;
  private String nome;
  private Date dtNascimento;  
  private Character sexo;
}
