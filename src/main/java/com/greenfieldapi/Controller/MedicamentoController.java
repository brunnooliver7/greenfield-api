package com.greenfieldapi.Controller;

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

import com.greenfieldapi.Model.Medicamento;
import com.greenfieldapi.Service.MedicamentoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medicamento")
@RequiredArgsConstructor
public class MedicamentoController {
  
  private final MedicamentoService medicamentoService;

  @GetMapping("/{id}")
  public Medicamento findById(@PathVariable Long id) {
    return medicamentoService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Medicamento save(@RequestBody Medicamento medicamento) {
    return medicamentoService.save(medicamento);
  }

  @PutMapping
  public Medicamento update(@RequestBody Medicamento medicamento) {
    return medicamentoService.update(medicamento);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    medicamentoService.delete(id);
  }
}
