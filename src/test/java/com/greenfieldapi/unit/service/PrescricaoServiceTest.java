package com.greenfieldapi.unit.service;

import static com.greenfieldapi.TestUtils.criarMedico;
import static com.greenfieldapi.TestUtils.criarPaciente;
import static com.greenfieldapi.TestUtils.criarPrescricao;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}
