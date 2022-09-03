package com.greenfieldapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.domain.model.Prescricao;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long>{
  List<Prescricao> findByMedicoId(Long medicoId);
  List<Prescricao> findByPacienteId(Long pacienteId);
}
