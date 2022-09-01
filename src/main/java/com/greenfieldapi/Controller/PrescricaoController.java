package com.greenfieldapi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenfieldapi.Model.Prescricao;
import com.greenfieldapi.Service.PrescricaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prescricao")
@RequiredArgsConstructor
public class PrescricaoController {
  
  private final PrescricaoService prescricaoService;

  @GetMapping("/medico/{medicoId}")
  public List<Prescricao> findByMedicoId(@PathVariable Long medicoId) {
    return prescricaoService.findByMedicoId(medicoId);
  }

  @GetMapping("/paciente/{pacienteId}")
  public List<Prescricao> findByPacienteId(@PathVariable Long pacienteId) {
    return prescricaoService.findByPacienteId(pacienteId);
  }

  @GetMapping("/{id}")
  public Prescricao findById(@PathVariable Long id) {
    return prescricaoService.findById(id);
  }

  @PostMapping
  public Prescricao save(@RequestBody Prescricao prescricao) {
   return prescricaoService.save(prescricao);
  }  
}
