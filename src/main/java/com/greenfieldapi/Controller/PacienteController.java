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

import com.greenfieldapi.Model.Paciente;
import com.greenfieldapi.Service.PacienteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
public class PacienteController {
  
  private final PacienteService pacienteService;

  @GetMapping
  public List<Paciente> findAll() {
    return pacienteService.findAll();
  }

  @GetMapping("/{id}")
  public Paciente findById(@PathVariable Long id) {
    return pacienteService.findById(id);
  }

  @PostMapping
  public Paciente save(@RequestBody Paciente paciente) {
    return pacienteService.save(paciente);
  }

  @PutMapping
  public Paciente update(@RequestBody Paciente paciente) {
    return pacienteService.update(paciente);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    pacienteService.delete(id);
  }
}
