package com.greenfieldapi.domain.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenfieldapi.domain.exception.EntidadeNaoEncontrada.MedicoNaoEncontradoException;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.repository.MedicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicoService {

  private final MedicoRepository medicoRepository;

  public List<Medico> findAll() {
    return medicoRepository.findAll();
  }

  public Medico findById(Long id) {
    return medicoRepository.findById(id).orElseThrow(() -> new MedicoNaoEncontradoException(id));
  }

  @Transactional
  public Medico save(Medico medico) {
    return medicoRepository.save(medico);
  }

  @Transactional
  public void delete(Long id) {
    try {
      medicoRepository.deleteById(id);
      medicoRepository.flush();
    } catch (EmptyResultDataAccessException e) {
      throw new MedicoNaoEncontradoException(id);
    }
  }
}
