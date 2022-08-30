package com.greenfieldapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.Model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> { 
}
