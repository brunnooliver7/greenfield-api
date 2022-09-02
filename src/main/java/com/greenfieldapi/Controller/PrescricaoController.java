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

import com.greenfieldapi.DTO.PrescricaoDTO;
import com.greenfieldapi.Mapper.PrescricaoMapper;
import com.greenfieldapi.Model.Prescricao;
import com.greenfieldapi.Service.PrescricaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prescricao")
@RequiredArgsConstructor
public class PrescricaoController {

  private final PrescricaoService prescricaoService;

  @GetMapping("/medico/{medicoId}")
  public List<PrescricaoDTO> findByMedicoId(@PathVariable Long medicoId) {
    List<Prescricao> prescricoes = prescricaoService.findByMedicoId(medicoId);
    return PrescricaoMapper.INSTANCE.toDTOs(prescricoes);
  }

  @GetMapping("/paciente/{pacienteId}")
  public List<PrescricaoDTO> findByPacienteId(@PathVariable Long pacienteId) {
    List<Prescricao> prescricoes = prescricaoService.findByPacienteId(pacienteId);
    return PrescricaoMapper.INSTANCE.toDTOs(prescricoes);
  }

  @GetMapping("/{id}")
  public PrescricaoDTO findById(@PathVariable Long id) {
    Prescricao prescricao = prescricaoService.findById(id);
    return PrescricaoMapper.INSTANCE.toDTO(prescricao);
  }

  @PostMapping
  public PrescricaoDTO save(@RequestBody PrescricaoDTO prescricaoDTO) {
    Prescricao prescricao = PrescricaoMapper.INSTANCE.toEntity(prescricaoDTO);
    prescricao = prescricaoService.save(prescricao);
    return PrescricaoMapper.INSTANCE.toDTO(prescricao);
  }

  @PutMapping
  public PrescricaoDTO update(@RequestBody PrescricaoDTO prescricaoDTO) {
    Prescricao prescricao = PrescricaoMapper.INSTANCE.toEntity(prescricaoDTO);
    prescricao = prescricaoService.update(prescricao);
    return PrescricaoMapper.INSTANCE.toDTO(prescricao);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    prescricaoService.delete(id);
  }
}
