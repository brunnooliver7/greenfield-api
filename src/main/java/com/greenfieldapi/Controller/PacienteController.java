package com.greenfieldapi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenfieldapi.DTO.PacienteDTO;
import com.greenfieldapi.Mapper.PacienteMapper;
import com.greenfieldapi.Model.Paciente;
import com.greenfieldapi.Service.PacienteService;

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
  public PacienteDTO save(@RequestBody PacienteDTO pacienteDTO) {
    Paciente paciente = PacienteMapper.INSTANCE.toEntity(pacienteDTO);
    paciente = pacienteService.save(paciente);
    return PacienteMapper.INSTANCE.toDTO(paciente);
  }

  @PutMapping
  public PacienteDTO update(@RequestBody PacienteDTO pacienteDTO) {
    Paciente paciente = PacienteMapper.INSTANCE.toEntity(pacienteDTO);
    paciente = pacienteService.update(paciente);
    return PacienteMapper.INSTANCE.toDTO(paciente);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    pacienteService.delete(id);
  }
}
