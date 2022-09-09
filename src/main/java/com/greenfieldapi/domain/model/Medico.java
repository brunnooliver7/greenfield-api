package com.greenfieldapi.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_medico")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  @CPF
  private String cpf;
  
  @Column(nullable = false)
  @NotBlank
  @Email
  private String email;
  
  @Column(nullable = false)
  @NotBlank
  private String nome;
  
  @Column(nullable = false)
  @NotNull
  @PastOrPresent
  private Date dtNascimento;
  
  @Column(nullable = false)
  @NotBlank
  private String crm;
  
  @Column(nullable = false)
  @NotBlank
  private String estadoRegistroCrm;
  
  @Column(nullable = false)
  @NotBlank
  private String estado;
  
  @Column(nullable = false)
  @NotNull
  @Size(min = 1)
  private String sexo;
  
  @Column(nullable = false)
  @NotBlank
  private String senha;

  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
  private List<Prescricao> prescricoes;
}
