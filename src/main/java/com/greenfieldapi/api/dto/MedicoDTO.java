package com.greenfieldapi.api.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoDTO {
  private Long id;

  @NotBlank
  @CPF
  private String cpf;
  
  @NotBlank
  @Email
  private String email;
  
  @NotBlank
  private String nome;
  
  @NotNull
  @PastOrPresent
  private Date dtNascimento;
  
  @NotBlank
  private String crm;
  
  @NotBlank
  private String estadoRegistroCrm;
  
  @NotBlank
  private String estado;
  
  @NotBlank
  @Size(min = 1)
  private String sexo;
  
  @NotBlank
  private String senha;
}
