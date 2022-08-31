package com.greenfieldapi.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_medicamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String descricao;

  private Integer quantidade;

  private String dosagem;

  private String frequencia;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "prescricao_id", nullable = false)
  private Prescricao prescricao;

  @Column(name = "prescricao_id", insertable = false, updatable = false)
  private Long prescricaoId;
}
