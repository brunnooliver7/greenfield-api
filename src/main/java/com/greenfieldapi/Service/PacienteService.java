package com.greenfieldapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greenfieldapi.Model.Paciente;
import com.greenfieldapi.Repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {
  
  private final PacienteRepository pacienteRepository;

  public List<Paciente> findAll() {
    return pacienteRepository.findAll();
  }

  public Paciente findById(Long id) {
    return pacienteRepository.findById(id).get();
  }

  public Paciente save(Paciente paciente) {
    return pacienteRepository.save(paciente);
  }

  public Paciente update(Paciente paciente) {
    Paciente pacienteSalvo = pacienteRepository.findById(paciente.getId()).get();
    paciente.setId(pacienteSalvo.getId());
    return pacienteRepository.save(paciente);
  }

  public void delete(Long id) {
    pacienteRepository.deleteById(id);
  }

}
