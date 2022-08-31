package com.greenfieldapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.Model.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long>{
}
