package com.greenfieldapi.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_medicamento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  private String descricao;
  
  @Positive
  private Integer quantidade;
  
  private String dosagem;
  
  private String frequencia;

  @CreationTimestamp
  @Column(name= "dt_cadastro", nullable = false, columnDefinition = "timestamp")
  private OffsetDateTime dtCadastro;

  @UpdateTimestamp
  @Column(name= "dt_atualizacao", nullable = false, columnDefinition = "timestamp")
  private OffsetDateTime dtAtualizacao;

}
