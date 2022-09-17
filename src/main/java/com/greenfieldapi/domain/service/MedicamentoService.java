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

@Service
@RequiredArgsConstructor
public class MedicamentoService {

  private final MedicamentoRepository medicamentoRepository;

  public Medicamento findById(Long id) {
    return medicamentoRepository.findById(id).orElseThrow(() -> 
      new MedicamentoNaoEncontradoException(id)
    );
  }

  @Transactional
  public Medicamento save(Medicamento medicamento) {
    return medicamentoRepository.save(medicamento);
  }

  @Transactional
  public void delete(Long id) {
    try {
      medicamentoRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new MedicamentoNaoEncontradoException(id);
    } catch (DataIntegrityViolationException e) {
      throw new MedicamentoEmUsoException(id);
    }
  }
}
