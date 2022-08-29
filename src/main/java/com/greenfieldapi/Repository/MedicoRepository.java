package com.greenfieldapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenfieldapi.Model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
