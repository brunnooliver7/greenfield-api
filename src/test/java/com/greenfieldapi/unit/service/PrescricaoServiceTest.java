package com.greenfieldapi.unit.service;

import static com.greenfieldapi.TestUtils.criarMedico;
import static com.greenfieldapi.TestUtils.criarPaciente;
import static com.greenfieldapi.TestUtils.criarPrescricao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.greenfieldapi.domain.model.Medico;
import com.greenfieldapi.domain.model.Paciente;
import com.greenfieldapi.domain.model.Prescricao;
import com.greenfieldapi.domain.repository.PrescricaoRepository;
import com.greenfieldapi.domain.service.PrescricaoService;

@ExtendWith(MockitoExtension.class)
public class PrescricaoServiceTest {

  @InjectMocks
  private PrescricaoService prescricaoService;

  @Mock
  private PrescricaoRepository prescricaoRepository;

  @Test
  public void deve_criar_uma_prescricao() {

    Medico medico = criarMedico("58896718040", "a@email", "001");
    Paciente paciente = criarPaciente("91354036085");
    Prescricao prescricao = criarPrescricao(medico.getId(), paciente.getId());

    when(prescricaoRepository.save(prescricao)).thenReturn(prescricao);

    prescricao = prescricaoService.save(prescricao);

    assertNotNull(prescricao);
    verify(prescricaoRepository, times(1)).save(prescricao);
  }

  @Test
  public void deve_obter_todas_as_prescricoes_de_um_medico() {

    Medico medico = criarMedico("58896718040", "a@email", "001");
    Paciente paciente = criarPaciente("91354036085");
    Prescricao prescricao1 = criarPrescricao(medico.getId(), paciente.getId());
    Prescricao prescricao2 = criarPrescricao(medico.getId(), paciente.getId());

    when(prescricaoRepository.findByMedicoId(medico.getId()))
      .thenReturn(Arrays.asList(prescricao1, prescricao2));

    List<Prescricao> prescricoes = prescricaoService.findByMedicoId(medico.getId());

    assertNotNull(prescricoes);
    assertEquals(prescricoes.size(), 2);
    verify(prescricaoRepository, times(1)).findByMedicoId(medico.getId());
  }

  @Test
  public void deve_obter_todas_as_prescricoes_de_um_paciente() {

    Medico medico = criarMedico("58896718040", "a@email", "001");
    Paciente paciente = criarPaciente("91354036085");
    Prescricao prescricao1 = criarPrescricao(medico.getId(), paciente.getId());
    Prescricao prescricao2 = criarPrescricao(medico.getId(), paciente.getId());

    when(prescricaoRepository.findByPacienteId(paciente.getId()))
      .thenReturn(Arrays.asList(prescricao1, prescricao2));

    List<Prescricao> prescricoes = prescricaoService.findByPacienteId(paciente.getId());

    assertNotNull(prescricoes);
    assertEquals(prescricoes.size(), 2);
    verify(prescricaoRepository, times(1)).findByPacienteId(paciente.getId());
  }

  @Test
  public void deve_obter_uma_prescricao() {

    Prescricao prescricao = criarPrescricao(1L, 1L);

    when(prescricaoRepository.findById(1L)).thenReturn(Optional.ofNullable(prescricao));

    prescricao = prescricaoService.findById(1L);

    assertNotNull(prescricao);
    verify(prescricaoRepository, times(1)).findById(1L);
  }

  @Test
  public void deve_deletar_uma_prescricao() {
    prescricaoService.delete(1L);
    verify(prescricaoRepository, times(1)).deleteById(anyLong());
  }

}
