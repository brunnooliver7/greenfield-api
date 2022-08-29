package com.greenfieldapi.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenfieldapi.Model.Medico;
import com.greenfieldapi.Service.MedicoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medico")
@RequiredArgsConstructor
public class MedicoController {
  
  private final MedicoService medicoService;

  @PostMapping
  public Medico save(@RequestBody Medico medico) {
    return medicoService.save(medico);
  }

  @GetMapping
  public List<Medico> findAll() {
    return medicoService.findAll();
  }
}
