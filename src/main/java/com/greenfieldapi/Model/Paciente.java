package com.greenfieldapi.Model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "Tb_Paciente")
@Data
public class Paciente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cpf;

  private String nome;

  @Column(name = "dt_nascimento")
  private Date dtNascimento;
  
  private Character sexo;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Prescricao> prescricoes;
}
