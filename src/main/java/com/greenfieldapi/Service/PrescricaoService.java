package com.greenfieldapi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greenfieldapi.Model.Prescricao;
import com.greenfieldapi.Repository.PrescricaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescricaoService {

  private final PrescricaoRepository prescricaoRepository;

  public List<Prescricao> findByMedicoId(Long medicoId) {
    return prescricaoRepository.findByMedicoId(medicoId);
  }

  public List<Prescricao> findByPacienteId(Long pacienteId) {
    return prescricaoRepository.findByPacienteId(pacienteId);
  }

  public Prescricao findById(Long id) {
    return prescricaoRepository.findById(id).get();
  }

  public Prescricao save(Prescricao prescricao) {
    return prescricaoRepository.save(prescricao);
  }

  public Prescricao update(Prescricao prescricao) {
    return prescricaoRepository.save(prescricao);
  }

  public void delete(Long id) {
    prescricaoRepository.deleteById(id);
  }
}
