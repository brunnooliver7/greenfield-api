package com.greenfieldapi.domain.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.PrescricaoNaoEncontradaException;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.PrescricaoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrescricaoService {

  private final PrescricaoRepository prescricaoRepository;

  public List<Prescricao> findByMedicoId(Long medicoId) {
    log.info(String.format("Buscando todas as prescricoes do medico (medicoId = %d)", medicoId));
    return prescricaoRepository.findByMedicoId(medicoId);
  }

  public List<Prescricao> findByPacienteId(Long pacienteId) {
    log.info(String.format("Buscando todas as prescricoes do paciente (pacienteId = %d)", pacienteId));
    return prescricaoRepository.findByPacienteId(pacienteId);
  }

  public Prescricao findById(Long id) {
    log.info(String.format("Buscando prescricao (id = %d)", id));
    return prescricaoRepository.findById(id).orElseThrow(() -> new PrescricaoNaoEncontradaException(id));
  }

  @Transactional
  public Prescricao save(Prescricao prescricao) {
    log.info("Salvando prescricao");
    return prescricaoRepository.save(prescricao);
  }

  @Transactional
  public void delete(Long id) {
    try {
      log.info(String.format("Deletando prescricao (id = %d)", id));
      prescricaoRepository.deleteById(id);
      prescricaoRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new PrescricaoNaoEncontradaException(id);
    }
  }
}
