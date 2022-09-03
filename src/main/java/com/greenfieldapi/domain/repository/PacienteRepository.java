package com.greenfieldapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.domain.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> { 
}
