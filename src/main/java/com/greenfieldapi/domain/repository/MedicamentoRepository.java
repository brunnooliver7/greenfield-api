package com.greenfieldapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.domain.model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
}
