package com.greenfieldapi.api.controller;

import java.util.List;

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

import com.greenfieldapi.api.dto.PacienteDTO;
import com.greenfieldapi.api.mapper.PacienteMapper;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {

  private final PacienteService pacienteService;

  @GetMapping
  public List<PacienteDTO> findAll() {
    List<Paciente> pacientes = pacienteService.findAll();
    return PacienteMapper.INSTANCE.toDTOs(pacientes);
  }

  @GetMapping("/{id}")
  public PacienteDTO findById(@PathVariable Long id) {
    Paciente paciente = pacienteService.findById(id);
    return PacienteMapper.INSTANCE.toDTO(paciente);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PacienteDTO save(@RequestBody PacienteDTO pacienteDTO) {
    Paciente paciente = PacienteMapper.INSTANCE.toEntity(pacienteDTO);
    paciente = pacienteService.save(paciente);
    return PacienteMapper.INSTANCE.toDTO(paciente);
  }

  @PutMapping
  public PacienteDTO update(@RequestBody PacienteDTO pacienteDTO) {
    Paciente pacienteNovo = PacienteMapper.INSTANCE.toEntity(pacienteDTO);
    Paciente pacienteAtual = pacienteService.findById(pacienteNovo.getId());
    BeanUtils.copyProperties(pacienteNovo, pacienteAtual, "id");
    pacienteNovo = pacienteService.save(pacienteNovo);
    return PacienteMapper.INSTANCE.toDTO(pacienteNovo);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    pacienteService.delete(id);
  }
}
