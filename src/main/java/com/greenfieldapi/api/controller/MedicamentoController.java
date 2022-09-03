package com.greenfieldapi.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.greenfieldapi.api.dto.MedicamentoDTO;
import com.greenfieldapi.api.mapper.MedicamentoMapper;
import com.greenfieldapi.domain.model.Medicamento;
import com.greenfieldapi.domain.service.MedicamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medicamento")
@RequiredArgsConstructor
public class MedicamentoController {
  
  private final MedicamentoService medicamentoService;

  @GetMapping("/{id}")
  public MedicamentoDTO findById(@PathVariable Long id) {
    Medicamento medicamento = medicamentoService.findById(id);
    return MedicamentoMapper.INSTANCE.toDTO(medicamento);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MedicamentoDTO save(@RequestBody MedicamentoDTO medicamentoDTO) {
    Medicamento medicamento = MedicamentoMapper.INSTANCE.toEntity(medicamentoDTO);
    medicamento = medicamentoService.save(medicamento);
    return MedicamentoMapper.INSTANCE.toDTO(medicamento);
  }

  @PutMapping
  public MedicamentoDTO update(@RequestBody MedicamentoDTO medicamentoDTO) {
    Medicamento medicamentoNovo = MedicamentoMapper.INSTANCE.toEntity(medicamentoDTO);
    Medicamento medicamentoAtual = medicamentoService.findById(medicamentoNovo.getId());
    BeanUtils.copyProperties(medicamentoNovo, medicamentoAtual, "id");
    medicamentoNovo = medicamentoService.save(medicamentoNovo);
    return MedicamentoMapper.INSTANCE.toDTO(medicamentoNovo);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    medicamentoService.delete(id);
  }
}
