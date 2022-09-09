package com.greenfieldapi.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_prescricao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prescricao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "medico_id", insertable = false, updatable = false)
  private Medico medico;
  
  @Column(name = "medico_id", nullable = false)
  @NotNull
  private Long medicoId;
  
  @ManyToOne
  @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
  private Paciente paciente;

  @Column(name = "paciente_id", nullable = false)
  @NotNull
  private Long pacienteId;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Medicamento> medicamentos;
}
