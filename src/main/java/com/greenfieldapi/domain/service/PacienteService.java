package com.greenfieldapi.domain.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.PacienteNaoEncontradoException;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
  
  private final PacienteRepository pacienteRepository;

  public List<Paciente> findAll() {
    return pacienteRepository.findAll();
  }

  public Paciente findById(Long id) {
    return pacienteRepository.findById(id).orElseThrow(() -> new PacienteNaoEncontradoException(id));
  }

  public Paciente save(Paciente paciente) {
    return pacienteRepository.save(paciente);
  }

  public void delete(Long id) {
    try {
      pacienteRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new PacienteNaoEncontradoException(id);
    }
  }

}
