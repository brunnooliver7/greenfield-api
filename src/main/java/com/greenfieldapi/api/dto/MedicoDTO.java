package com.greenfieldapi.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoDTO {
  private Long id;
  private String cpf;
  private String email;
  private String nome;
  private Date dtNascimento;
  private String crm;
  private String estadoRegistroCrm;
  private String estado;
  private Character sexo;
  private String senha;
}
