package com.greenfieldapi.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.greenfieldapi.api.dto.MedicoDTO;
import com.greenfieldapi.api.mapper.MedicoMapper;
import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.service.MedicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/medico")
@RequiredArgsConstructor
public class MedicoController {

  private final MedicoService medicoService;

  @GetMapping
  public List<MedicoDTO> findAll() {
    List<Medico> medicos = medicoService.findAll();
    return MedicoMapper.INSTANCE.toDTOs(medicos);
  }

  @GetMapping("/{id}")
  public MedicoDTO findById(@PathVariable Long id) {
    Medico medico = medicoService.findById(id);
    return MedicoMapper.INSTANCE.toDTO(medico);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MedicoDTO save(@RequestBody @Valid MedicoDTO medicoDTO) {
    Medico medico = MedicoMapper.INSTANCE.toEntity(medicoDTO);
    medico = medicoService.save(medico);
    return MedicoMapper.INSTANCE.toDTO(medico);
  }

  @PutMapping
  public MedicoDTO update(@RequestBody @Valid MedicoDTO medicoDTO) {
    Medico medicoNovo = MedicoMapper.INSTANCE.toEntity(medicoDTO);
    Medico medicoAtual = medicoService.findById(medicoNovo.getId());
    BeanUtils.copyProperties(medicoNovo, medicoAtual, "id");
    medicoNovo = medicoService.save(medicoNovo);
    return MedicoMapper.INSTANCE.toDTO(medicoNovo);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    medicoService.delete(id);
  }
}
