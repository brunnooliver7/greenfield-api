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

import com.greenfieldapi.Model.Medico;
import com.greenfieldapi.Service.MedicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medico")
@RequiredArgsConstructor
public class MedicoController {
  
  private final MedicoService medicoService;

  @GetMapping
  public List<Medico> findAll() {
    return medicoService.findAll();
  }

  @GetMapping("/{id}")
  public Medico findById(@PathVariable Long id) {
    return medicoService.findById(id);
  }

  @PostMapping
  public Medico save(@RequestBody Medico medico) {
    return medicoService.save(medico);
  }

  @PutMapping
  public Medico update(@RequestBody Medico medico) {
    return medicoService.update(medico);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    medicoService.delete(id);
  }
}
