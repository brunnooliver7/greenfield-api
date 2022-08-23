package com.greenfieldapi.Model;

import java.util.Date;
import java.util.List;

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
@Table(name = "medico")
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

  private String Nome;

  private Date dtNascimento;

  private String crm;

  private String estadoRegistroCrm;

  private String estado;

  private Character sexo;

  private String senha;

  @OneToMany(mappedBy = "medico")
  private List<Prescricao> prescricoes;
}
