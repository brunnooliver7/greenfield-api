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

  @Column(nullable = false)
  private String cpf;
  
  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String nome;
  
  @Column(nullable = false)
  private Date dtNascimento;
  
  @Column(nullable = false)
  private String crm;
  
  @Column(nullable = false)
  private String estadoRegistroCrm;
  
  @Column(nullable = false)
  private String estado;
  
  @Column(nullable = false)
  private Character sexo;
  
  @Column(nullable = false)
  private String senha;

  @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
  private List<Prescricao> prescricoes;
}
