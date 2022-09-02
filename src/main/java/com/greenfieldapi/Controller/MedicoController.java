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

import com.greenfieldapi.DTO.MedicoDTO;
import com.greenfieldapi.Mapper.MedicoMapper;
import com.greenfieldapi.Model.Medico;
import com.greenfieldapi.Service.MedicoService;

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
  public MedicoDTO save(@RequestBody MedicoDTO medicoDTO) {
    Medico medico = MedicoMapper.INSTANCE.toEntity(medicoDTO);
    medico = medicoService.save(medico);
    return MedicoMapper.INSTANCE.toDTO(medico);
  }

  @PutMapping
  public MedicoDTO update(@RequestBody MedicoDTO medicoDTO) {
    Medico medico = MedicoMapper.INSTANCE.toEntity(medicoDTO);
    medico = medicoService.update(medico);
    return MedicoMapper.INSTANCE.toDTO(medico);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    medicoService.delete(id);
  }
}
