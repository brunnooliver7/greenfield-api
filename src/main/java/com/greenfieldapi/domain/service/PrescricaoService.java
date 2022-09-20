package com.greenfieldapi.domain.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.PrescricaoNaoEncontradaException;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.PrescricaoRepository;

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
    return prescricaoRepository.findById(id).orElseThrow(() -> new PrescricaoNaoEncontradaException(id));
  }

  @Transactional
  public Prescricao save(Prescricao prescricao) {
    return prescricaoRepository.save(prescricao);
  }

  @Transactional
  public void delete(Long id) {
    try {
      prescricaoRepository.deleteById(id);
      prescricaoRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new PrescricaoNaoEncontradaException(id);
    }
  }
}
