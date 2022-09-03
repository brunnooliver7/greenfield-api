package com.greenfieldapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.domain.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
