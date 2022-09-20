package com.greenfieldapi.domain.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.PacienteNaoEncontradoException;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteService {
  
  private final PacienteRepository pacienteRepository;

  public List<Paciente> findAll() {
    log.info("Buscando todos os pacientes");
    return pacienteRepository.findAll();
  }

  public Paciente findById(Long id) {
    log.info(String.format("Buscando paciente (id = %d)", id));
    return pacienteRepository.findById(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));
  }

  @Transactional
  public Paciente save(Paciente paciente) {
    log.info("Salvando paciente");
    return pacienteRepository.save(paciente);
  }

  @Transactional
  public void delete(Long id) {
    try {
      log.info(String.format("Deletando paciente (id = %d)", id));
      pacienteRepository.deleteById(id);
      pacienteRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new PacienteNaoEncontradoException(id);
    }
  }

}
