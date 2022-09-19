package com.greenfieldapi.domain.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_paciente")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  @NotBlank
  @CPF
  private String cpf;
  
  @Column(nullable = false)
  @NotBlank
  private String nome;

  @Column(name = "dt_nascimento", nullable = false)
  @NotNull
  @PastOrPresent
  private OffsetDateTime dtNascimento;
  
  @Column(nullable = false)
  @NotNull
  @Size(min = 1)
  private String sexo;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Prescricao> prescricoes;
}
