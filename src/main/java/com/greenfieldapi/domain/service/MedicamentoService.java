package com.greenfieldapi.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenfieldapi.domain.exception.EntidadeEmUso.MedicamentoEmUsoException;
import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.MedicamentoNaoEncontradoException;
import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.repository.MedicamentoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicamentoService {

  private final MedicamentoRepository medicamentoRepository;

  public Medicamento findById(Long id) {
    log.info(String.format("Buscando medicamento (id = %d)", id));
    return medicamentoRepository.findById(id).orElseThrow(() -> 
      new MedicamentoNaoEncontradoException(id)
    );
  }

  @Transactional
  public Medicamento save(Medicamento medicamento) {
    log.info("Salvando medicamento");
    return medicamentoRepository.save(medicamento);
  }

  @Transactional
  public void delete(Long id) {
    try {
      log.info(String.format("Deletando medicamento (id = %d)", id));
      medicamentoRepository.deleteById(id);
      medicamentoRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new MedicamentoNaoEncontradoException(id);
    } catch (DataIntegrityViolationException e) {
      throw new MedicamentoEmUsoException(id);
    }
  }
}
