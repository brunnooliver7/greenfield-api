package com.greenfieldapi.Service;

import org.springframework.stereotype.Service;

import com.greenfieldapi.Model.Medicamento;
import com.greenfieldapi.Model.Prescricao;
import com.greenfieldapi.Repository.MedicamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicamentoService {
  
  private final MedicamentoRepository medicamentoRepository;

  public Medicamento findById(Long id) {
    return medicamentoRepository.findById(id).get();
  }

  public Medicamento save(Medicamento medicamento) {
    Prescricao prescricao = new Prescricao();
    prescricao.setId(medicamento.getPrescricaoId());
    medicamento.setPrescricao(prescricao);
    
    return medicamentoRepository.save(medicamento);
  }

  public Medicamento update(Medicamento medicamento) {
    Medicamento medicamentoSalvo = medicamentoRepository.findById(medicamento.getId()).get();
    medicamento.setId(medicamentoSalvo.getId());

    Prescricao prescricao = new Prescricao();
    prescricao.setId(medicamento.getPrescricaoId());
    medicamento.setPrescricao(prescricao);

    return medicamentoRepository.save(medicamento);
  }

  public void delete(Long id) {
    medicamentoRepository.deleteById(id);
  }
}
