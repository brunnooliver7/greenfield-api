package com.greenfieldapi.api.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoDTO {
  private Long id;

  @NotNull
  private String cpf;
  
  @NotNull
  @Email
  private String email;
  
  @NotNull
  private String nome;
  
  @NotNull
  private Date dtNascimento;
  
  @NotNull
  private String crm;
  
  @NotNull
  private String estadoRegistroCrm;
  
  @NotNull
  private String estado;
  
  @NotNull
  private Character sexo;
  
  @NotNull
  private String senha;
}
