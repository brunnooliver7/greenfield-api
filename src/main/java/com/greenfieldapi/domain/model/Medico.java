package com.greenfieldapi.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Prescricao> prescricoes;
}
